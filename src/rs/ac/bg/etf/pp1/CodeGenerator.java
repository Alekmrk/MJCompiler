package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

    private int varCount;

    private int paramCnt;

    private int mainPc;

    private int constVal;

    public int getMainPc() {
        return mainPc;
    }

    @Override
    public void visit(MethodType methodType) {
        // ovo ne mora ako main moze da bude samo void
        if ("main".equalsIgnoreCase(methodType.getMethName())) {
            mainPc = Code.pc;
        }
        methodType.obj.setAdr(Code.pc);

        // Collect arguments and local variables.
        SyntaxNode methodNode = methodType.getParent();
        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);
        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry.
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(varCnt.getCount() + fpCnt.getCount());
    }

    @Override
    public void visit(MethodVoid methodVoid) {
        if ("main".equalsIgnoreCase(methodVoid.getMethName())) {
            mainPc = Code.pc;
        }
        methodVoid.obj.setAdr(Code.pc);

        // Collect arguments and local variables.
        SyntaxNode methodNode = methodVoid.getParent();
        VarCounter varCnt = new VarCounter();
        methodNode.traverseTopDown(varCnt);
        FormParamCounter fpCnt = new FormParamCounter();
        methodNode.traverseTopDown(fpCnt);

        // Generate the entry.
        Code.put(Code.enter);
        Code.put(fpCnt.getCount());
        Code.put(varCnt.getCount() + fpCnt.getCount());
    }

    @Override
    public void visit(VarDecl VarDecl) {
        varCount++;
    }

    @Override
    public void visit(FormalParamDecl FormalParam) {
        paramCnt++;
    }

    public void visit(PrintStmt printStatement) {
        if (printStatement.getExpr().obj.getType().equals(Tab.charType)) {
            Code.loadConst(1);
            Code.put(Code.bprint);
        } else {
            Code.loadConst(5);
            Code.put(Code.print);
        }
    }

    public void visit(ConstNumber constNumber) {
        if (constNumber.getParent().getClass() == ConstDecl.class || constNumber.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constNumber.obj.getType());
        con.setLevel(0);
        con.setAdr(constNumber.getVal());
        Code.load(con);
        constVal = constNumber.getVal();
        //Code.load(new Obj(Obj.Con, "$", Const.struct, Const.getN1(), 0));
    }

    public void visit(ConstChar constChar) {
        if (constChar.getParent().getClass() == ConstDecl.class || constChar.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constChar.obj.getType());
        con.setLevel(0);
        con.setAdr(constChar.getVal());
        Code.load(con);
        constVal = constChar.getVal();
        //char c;
        //Code.store(constChar.obj);
        //Code.store(Obj o)
    }

    public void visit(ConstBool constBool) {
        if (constBool.getParent().getClass() == ConstDecl.class || constBool.getParent().getClass() == ConstDeclListExtendedVal.class) {
            return;
        }
        Obj con = Tab.insert(Obj.Con, "$", constBool.obj.getType());
        con.setLevel(0);// ovde ispisuje kao 1 kada je true i kao 0 kad je false
        int val = constBool.getVal() ? 1 : 0;
        con.setAdr(val);
        Code.load(con);
        constVal = val;
    }

    @Override
    public void visit(MethodDecl MethodDecl) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    @Override
    public void visit(EqualDesStatement equalDesStatement) {
        //System.out.println(equalDesStatement);

        Code.store(equalDesStatement.getDesignator().obj);
    }

    @Override
    public void visit(DesignatorIdent designatorIdent) {
        SyntaxNode parent = designatorIdent.getParent();
        //ako maknes ove uslove zakomentarisi onda u plus plus i minus minus( i obrnuto odradi)
        if (EqualDesStatement.class != parent.getClass())
        //&& IncDesStatement.class != parent.getClass() && DecDesStatement.class != parent.getClass())
        {

            /*if (DesignatorIdentExtended.class == parent.getClass()) {
                Code.put(Code.dup);
            }*/
        }
        Code.load(designatorIdent.obj);
        //Code.load(designatorIdent.obj);
    }

    @Override
    public void visit(DesignatorIdentExtended designatorIdentExtended) {
        SyntaxNode parent = designatorIdentExtended.getParent();

        //ako maknes ove uslove zakomentarisi onda u plus plus i minus minus( i obrnuto odradi)
        if (EqualDesStatement.class != parent.getClass())
        //&& IncDesStatement.class != parent.getClass() && DecDesStatement.class != parent.getClass())
        {
            //Code.put(Code.pop);
            //Code.loadConst(5);
            //Code.put(Code.getstatic);
            //Code.loadConst(designatorIdentExtended.obj.getAdr());
            //Code.put(Code.dup2);
            //Code.put(Code.pop);
            //Code.put(Code.dup_x1);
            //Code.put(Code.pop);
            if(IncDesStatement.class==parent.getClass() || DecDesStatement.class== parent.getClass()){
                //Code.put(Code.dup2);
            }
            Code.put(Code.dup2);
            Code.load(designatorIdentExtended.obj);//Code.loadConst(2);
            //Code.put(Code.dup);
        }

    }

    public void visit(IncDesStatement incDesStatement) {
        //Code.load(incDesStatement.getDesignator().obj);
        //if(incDesStatement.getParent().getClass()==)

        Code.loadConst(1);
        Code.put(Code.add);
        Code.store(incDesStatement.getDesignator().obj);
    }

    public void visit(DecDesStatement decDesStatement) {
        //Code.load(decDesStatement.getDesignator().obj);
        Code.loadConst(-1);
        Code.put(Code.add);
        Code.store(decDesStatement.getDesignator().obj);
    }

    public void visit(YesMinus yesMinus) {
        Code.put(Code.neg);
    }

    public void visit(YesAddopTermList yesAddopTermList) {
        if (yesAddopTermList.getAddop() instanceof AddopBasicLeft) {
            if ((((AddopBasicLeft) yesAddopTermList.getAddop())).getAddopLeft() instanceof AddopLeftPlus) {
                Code.put(Code.add);
            } else {
                Code.put(Code.sub);
            }
        } else if (yesAddopTermList.getAddop() instanceof AddopBasicRight) {
            if ((((AddopBasicRight) yesAddopTermList.getAddop())).getAddopRight() instanceof AddopRightPlusEqual) {
                //Code.put(Code.add); plus equal
                Code.put(Code.add);
                Code.put(Code.dup);
            } else {
                //Code.put(Code.sub); minus equal
            }
        }
        //else if(expression.getAddop() instanceof Minus) Code.put(Code.sub);
    }

    public void visit(YesMulopFactorList yesMulopFactorList) {
        if (yesMulopFactorList.getMulop() instanceof MulopBasicLeft) {
            if ((((MulopBasicLeft) yesMulopFactorList.getMulop())).getMulopLeft() instanceof MulopLeftMul) {
                Code.put(Code.mul);
            } else if ((((MulopBasicLeft) yesMulopFactorList.getMulop())).getMulopLeft() instanceof MulopLeftDiv) {
                Code.put(Code.div);
            } else {
                Code.put(Code.rem);
            }
        } else if (yesMulopFactorList.getMulop() instanceof MulopBasicRight) {
            if ((((MulopBasicRight) yesMulopFactorList.getMulop())).getMulopRight() instanceof MulopRightMulEqual) {
                //Code.put(Code.add); mul equal
            } else if ((((MulopBasicRight) yesMulopFactorList.getMulop())).getMulopRight() instanceof MulopRightDivEqual) {

                //Code.put(Code.sub); div equal
            } else {
                //mod equal
            }
        }
    }

    public void visit(NewTypeExpr newTypeExpr) {
        int b = newTypeExpr.getType().obj.getType() == Tab.intType ? 1 : 0;
        Code.put(Code.newarray);
        Code.put(b);
    }
}

