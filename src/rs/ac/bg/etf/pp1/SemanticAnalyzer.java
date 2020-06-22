package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
    boolean errorDetected = false;
    int printCallCount = 0;
    Obj currentMethod = null;
    boolean returnFound = false;
    Struct currentType=null;
    boolean isArray=false;
    int nVars;
    boolean isMinus=false;

    Logger log = Logger.getLogger(getClass());


    public void report_error(String message, SyntaxNode info) {
        errorDetected = true;
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.error(msg.toString());
    }

    public void report_info(String message, SyntaxNode info) {
        StringBuilder msg = new StringBuilder(message);
        int line = (info == null) ? 0: info.getLine();
        if (line != 0)
            msg.append (" na liniji ").append(line);
        log.info(msg.toString());
    }

    public void visit(Program program) {
        nVars = Tab.currentScope.getnVars();
        Tab.chainLocalSymbols(program.getProgName().obj);
        Tab.closeScope();
    }

    public void visit(ProgName progName) {
        progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
        Tab.openScope();
    }

    public void visit(VarDecl varDecl){
        report_info("Deklarisana promenljiva "+ varDecl.getVarName(), varDecl);
        Struct type=varDecl.getType().obj.getType();
        if(isArray){
            report_info("Niz je promenljiva:"+ varDecl.getVarName(), varDecl);
            type = new Struct(Struct.Array, varDecl.getType().obj.getType());
            Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), type);
            isArray=false;
        }else {
            Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), type);
        }
        //currentType=varDecl.getType().obj.getType(); =null ako bi da vracam
        //varDecl.obj=varDecl.getVarDeclListExtended().obj;

    }

    public void visit(VarDecListExtendedYes varDecListExtendedYes){
        report_info("Deklarisana promenljiva "+ varDecListExtendedYes.getVarName(), varDecListExtendedYes);
        Struct type=currentType;
        if(isArray){
            report_info("Niz je promenljiva:"+ varDecListExtendedYes.getVarName(), varDecListExtendedYes);
            type = new Struct(Struct.Array, type);
            Obj varNode = Tab.insert(Obj.Var, varDecListExtendedYes.getVarName(), type);
            isArray=false;
        }else {
            Obj varNode = Tab.insert(Obj.Var, varDecListExtendedYes.getVarName(), type);
        }
    }

    public void visit(IsArray isArr){
        isArray=true;
    }

    public void visit(Type type) {
        Obj typeNode = Tab.find(type.getTypeName());
        if (typeNode == Tab.noObj) {
            report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
            type.obj = Tab.noObj;
        }
        else {
            if (Obj.Type == typeNode.getKind()) {
                type.obj = typeNode;
                currentType = typeNode.getType();
                //currentType = typeNode.getType().getElemType();
            }
            else {
                report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
                type.obj = Tab.noObj;
            }
        }
    }

    public void visit(ConstDecl constDecl){
        if(constDecl.getType().obj.getType()!=constDecl.getConstType().obj.getType()){
            report_error("Pokusaj inicijalizacije konstante "+ constDecl.getConstName()+" pogresnim tipom",null);
            return;
        }
        report_info("Deklarisana konstanta "+ constDecl.getConstName(), constDecl);
        Obj constNode = Tab.insert(Obj.Con, constDecl.getConstName(), constDecl.getType().obj.getType());
        //currentType=varDecl.getType().obj.getType(); =null ako bi da vracam
    }

    public void visit(ConstDeclListExtendedVal constDeclListExtendedVal){
        if(currentType!=constDeclListExtendedVal.getConstType().obj.getType()){
            report_error("Pokusaj inicijalizacije konstante "+ constDeclListExtendedVal.getConstName()+" pogresnim tipom",null);
            return;
        }
        report_info("Deklarisana konstanta "+ constDeclListExtendedVal.getConstName(), constDeclListExtendedVal);
        //if(currentType==constDeclListExtendedVal)
        Obj constNode = Tab.insert(Obj.Con, constDeclListExtendedVal.getConstName(), currentType);

    }

    public void visit(ConstNumber cnst){
        cnst.obj = new Obj(Obj.Con, "", Tab.intType, cnst.getVal() , Obj.NO_VALUE);
    }

    public void visit(ConstChar cnstChar){
        cnstChar.obj = new Obj(Obj.Con, "", Tab.charType, cnstChar.getVal() , Obj.NO_VALUE);
    }

    public void visit(ConstBool cnstBool){
       // cnstBool.obj = new Obj(Obj.Con, "", SymbolExtension.boolType, cnstBool.getVal() ? 1 : 0, Obj.NO_VALUE);
    }

    public void visit(MethodDecl methodDecl) {
        if (!returnFound && currentMethod.getType() != Tab.noType) {
            report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funkcija " + currentMethod.getName() + " nema return iskaz!", null);
        }
        Tab.chainLocalSymbols(currentMethod);
        Tab.closeScope();

        returnFound = false;
        currentMethod = null;
        isArray=false;
    }

    public void visit(MethodType methodType) {
        currentMethod = Tab.insert(Obj.Meth, methodType.getMethName(), methodType.getType().obj.getType());
        methodType.obj = currentMethod;
        Tab.openScope();
        report_info("Obradjuje se funkcija " + methodType.obj.getName(), methodType);
    }

    public void visit(MethodVoid methodVoid){
        currentMethod = Tab.insert(Obj.Meth, methodVoid.getMethName(), Tab.noType);
        methodVoid.obj = currentMethod;
        Tab.openScope();
        report_info("Obradjuje se void funkcija " + methodVoid.obj.getName(), methodVoid);
    }


    public void visit(ReturnStmtNoExpr returnStmtNoExpr){
        returnFound=true;
        /*
		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}	*/
    }

    public void visit(ReturnStmtExpr returnStmtExpr){
        returnFound=true;
        /*
		Struct currMethType = currentMethod.getType();
		if (!currMethType.compatibleWith(returnExpr.getExpr().struct)) {
			report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
		}	*/

    }

    public void visit(Assignment assignment){
        assignment.obj=assignment.getDesignatorStatement().obj;
    }

    public void visit(ReadStmt readStmt){
        Obj obj = Tab.find(readStmt.getDesignator().obj.getName());
        if (obj == Tab.noObj) {
            report_error("Greska na liniji " + readStmt.getDesignator().getLine()+ " : ime "+readStmt.getDesignator().obj.getName()+" nije deklarisano! ", null);
        }
    }
    public void visit(PrintStmt printStmt){
        Obj obj = Tab.find(printStmt.getExpr().obj.getName());
        if (obj == Tab.noObj) {
            report_error("Greska na liniji " + printStmt.getExpr().getLine()+ " : ime "+printStmt.getExpr().obj.getName()+" nije deklarisano! ", null);
        }
    }

    public void visit(IncDesStatement incDesStatement){
        if(incDesStatement.getDesignator().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + incDesStatement.getLine() + " : " + " moguce je uraditi postinkrement samo INT tipu. ", null);
        }
        if(incDesStatement.getDesignator().obj.getKind() != Obj.Var && incDesStatement.getDesignator().obj.getKind() != Obj.Elem) {
            report_error("Nije moguce inkrementirati konstante", incDesStatement);
        }
    }
    public void visit(DecDesStatement decDesStatement){

        if(decDesStatement.getDesignator().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + decDesStatement.getLine() + " : " + " moguce je uraditi postdekrement samo INT tipu. ", null);
        }
        if(decDesStatement.getDesignator().obj.getKind() != Obj.Var && decDesStatement.getDesignator().obj.getKind() != Obj.Elem) {
            report_error("Nije moguce dekrementirati konstante", decDesStatement);
        }
    }

    public void visit(EqualDesStatement equalDesStatement){
        Struct a=equalDesStatement.getExpr().obj.getType();
        Struct b=equalDesStatement.getDesignator().obj.getType();
        if(a==null || b==null){
            report_error("nesto je null na liniji: "+equalDesStatement.getLine(),null);
        }
        if (!equalDesStatement.getExpr().obj.getType().equals(equalDesStatement.getDesignator().obj.getType())) {
            report_error("Greska na liniji " + equalDesStatement.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
        }
    }

    public void visit(AddExpr addExpr){

        if(isMinus && addExpr.getTerm().obj.getType()!=Tab.intType){
            isMinus=false;
            report_error("Greska na liniji " + addExpr.getTerm().getLine() + " : " + " ne moze se pisati minus za tipove koji nisu INT ", null);
        }
        currentType=addExpr.getTerm().obj.getType();
        //mora od faktora da se uzme i tako redom
        addExpr.obj=addExpr.getTerm().obj;
    }
    public void visit(YesMinus yesMinus){
        isMinus=true;
    }

    public void visit(YesAddopTermList yesAddopTermList){
        if(yesAddopTermList.getTerm().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + yesAddopTermList.getLine() + " : " + " ne moze se vrsiti operacija dodavanja za tipove koji nisu INT ", null);
        }
    }

    public void visit(YesMulopFactorList yesMulopFactorList){
        if(yesMulopFactorList.getFactor().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + yesMulopFactorList.getLine() + " : " + " ne moze se vrsiti operacija mnozenja za tipove koji nisu INT ", null);
        }
    }

    public void visit(TermFactor termFactor){
        termFactor.obj=termFactor.getFactor().obj;
    }

    public void visit(ConstTypeVal constTypeVal){
        constTypeVal.obj=constTypeVal.getConstType().obj;
    }

    public void visit(ExprLRPAREN exprLRPAREN) {
        exprLRPAREN.obj = exprLRPAREN.getExpr().obj;
    }
    public void visit(NewType newType) {
        newType.obj = newType.getType().obj;
    }

    public void visit(NewTypeExpr newTypeExpr) {
        //newTypeExpr.obj = newTypeExpr.getType().obj;
        if(newTypeExpr.getExpr().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + newTypeExpr.getLine()+ " nepravilno definisan niz sa tipom razlicitim od INT! ", null);
        }
        newTypeExpr.obj = new Obj(Obj.Var,"", new Struct(Struct.Array, newTypeExpr.getType().obj.getType()));
             //  newTypeExpr.getType().obj.getType().getElemType());
        //Obj.Var, "", new Struct(Struct.Array, newTypeArrayFactor.getType().obj.getType())
    }

    public void visit(DesignatorFactor designatorFactor) {
        designatorFactor.obj = designatorFactor.getDesignator().obj;
    }

    public void visit(DesignatorFactorExtended designatorFactorExtended) {
        designatorFactorExtended.obj = designatorFactorExtended.getDesignator().obj;
    }

    public void visit(DesignatorIdent designatorIdent){
        Obj obj = Tab.find(designatorIdent.getName());
        if (obj == Tab.noObj) {
            report_error("Greska na liniji " + designatorIdent.getLine()+ " : ime "+designatorIdent.getName()+" nije deklarisano! ", null);
        }
        designatorIdent.obj = new Obj(Obj.Var, designatorIdent.getName(), obj.getType());
    }

    public void visit(DesignatorIdentExtended designatorIdentExtended){
        Obj obj = Tab.find(designatorIdentExtended.getName());

        if (obj == Tab.noObj) {
            report_error("Greska na liniji " + designatorIdentExtended.getLine()+ " : ime "+designatorIdentExtended.getName()+" nije deklarisano! ", null);
        }
        if(designatorIdentExtended.getExpr().obj.getType()!=Tab.intType){
            report_error("Greska na liniji " + designatorIdentExtended.getLine()+ " nepravilno definisan niz sa tipom razlicitim od INT! ", null);
        }
        designatorIdentExtended.obj = new Obj(Obj.Elem,designatorIdentExtended.getName(), obj.getType().getElemType());

    }

    public boolean passed() {
        return !errorDetected;
    }
}

   /* public void visit(VarDecl varDecl) {
        report_info("Deklarisana promenljiva "+ varDecl.getVarName(), varDecl);
        Obj varNode = Tab.insert(Obj.Var, varDecl.getVarName(), varDecl.getType().obj.getType());
        varDecl.obj=varDecl.getVarDeclListExtended().obj;
    }
    public void visit(VarDeclListYes varDeclListYes) {
        report_info("Deklarisana promenljiva u zarezu"+ varDeclListYes.getVarDecl().obj.getName(), varDeclListYes);
        Obj varNode = Tab.insert(Obj.Var, varDeclListYes.getVarDecl().obj.getName(), varDeclListYes.obj.getType());
        varDeclListYes.obj=varDeclListYes.getVarDecl().obj;
    }

    public void visit(Type type) {
        Obj typeNode = Tab.find(type.getTypeName());
        if (typeNode == Tab.noObj) {
            report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola", null);
            type.obj = Tab.noObj;
        }
        else {
            if (Obj.Type == typeNode.getKind()) {
                type.obj = typeNode;
            }
            else {
                report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip ", type);
                type.obj = Tab.noObj;
            }
        }
    }

    public void visit(MethodDecl methodDecl) {
        if (!returnFound && currentMethod.getType() != Tab.noType) {
            report_error("Semanticka greska na liniji " + methodDecl.getLine() + ": funcija " + currentMethod.getName() + " nema return iskaz!", null);
        }

        Tab.chainLocalSymbols(currentMethod);
        Tab.closeScope();

        returnFound = false;
        currentMethod = null;
    }

    public void visit(MethodTypeName methodTypeName) {
        currentMethod = Tab.insert(Obj.Meth, methodTypeName.obj.getName(), methodTypeName.obj.getType());
        methodTypeName.obj = currentMethod;
        Tab.openScope();
        report_info("Obradjuje se funkcija " + methodTypeName.obj.getName(), methodTypeName);
    }

    public void visit(EqualDesStatement assignment) {
        if (!assignment.getExpr().obj.getType().assignableTo(assignment.getDesignator().obj.getType()))
            report_error("Greska na liniji " + assignment.getLine() + " : " + " nekompatibilni tipovi u dodeli vrednosti ", null);
    }

    public void visit(PrintStmt printStmt){
        printCallCount++;
    }

    public void visit(ReturnStmtExpr returnExpr){
        returnFound = true;
        Struct currMethType = currentMethod.getType();
        if (!currMethType.compatibleWith(returnExpr.getExpr().obj.getType())) {
            report_error("Greska na liniji " + returnExpr.getLine() + " : " + "tip izraza u return naredbi ne slaze se sa tipom povratne vrednosti funkcije " + currentMethod.getName(), null);
        }
    }

    public void visit(ProcCall procCall){
        Obj func = procCall.getDesignator().obj;
        if (Obj.Meth == func.getKind()) {
            report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + procCall.getLine(), null);
            //RESULT = func.getType();
        }
        else {
            report_error("Greska na liniji " + procCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            //RESULT = Tab.noType;
        }
    }

    public void visit(AddExpr addExpr) {
        //addExpr.struct = addExpr.getTerm().struct;
        Struct te = addExpr.getAddopTermList().obj.getType();
        Struct t = addExpr.getTerm().obj.getType();
        if (te.compatibleWith(t) && te == Tab.intType)
            addExpr.obj = addExpr.getAddopTermList().obj;
        else {
            report_error("Greska na liniji "+ addExpr.getLine()+" : nekompatibilni tipovi u izrazu za sabiranje.", null);
            addExpr.obj = Tab.noObj;
        }
    }

    public void visit(AddExpr termExpr) {
        termExpr.obj = termExpr.getTerm().obj;
    }
    public void visit(YesAddopTermList YesAddopTermList){
        YesAddopTermList.obj=YesAddopTermList.getAddopTermList().obj;
    }

    public void visit(Mulop mulop){

    }
    public void visit(TermFactor term) {
        term.obj = term.getFactor().obj;
    }

    public void visit(ConstNumber cnst){
        cnst.obj = new Obj(Obj.Con, "", Tab.intType, cnst.getVal() , Obj.NO_VALUE);
    }

    public void visit(ConstChar cnstChar){
        cnstChar.obj = new Obj(Obj.Con, "", Tab.charType, cnstChar.getVal() , Obj.NO_VALUE);
        //  cnst.obj = new Obj(Obj.Con, "", Tab.charType, cnst.getValue(), Obj.NO_VALUE);
    }

    public void visit(ConstBool cnstBool){
        //   cnstChar.struct = Tab.charType; cnst.obj = new Obj(Obj.Con, "", Tab.charType, cnst.getVal() , Obj.NO_VALUE);
    }

    public void visit(DesignatorFactor var) {
        var.obj = var.getDesignator().obj;
    }

   public void visit(FuncCall funcCall){
        Obj func = funcCall.getDesignator().obj;
        if (Obj.Meth == func.getKind()) {
            report_info("Pronadjen poziv funkcije " + func.getName() + " na liniji " + funcCall.getLine(), null);
            funcCall.struct = func.getType();
        }
        else {
            report_error("Greska na liniji " + funcCall.getLine()+" : ime " + func.getName() + " nije funkcija!", null);
            funcCall.struct = Tab.noType;
        }

    }

    public void visit(Designator designator){
        Obj obj = Tab.find(designator.obj.getName());
        if (obj == Tab.noObj) {
            report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.obj.getName()+" nije deklarisano! ", null);
        }
        designator.obj = obj;
    }*/