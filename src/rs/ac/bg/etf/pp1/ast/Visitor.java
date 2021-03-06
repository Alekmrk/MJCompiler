// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:13


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(Assignop Assignop);
    public void visit(MulopFactorList MulopFactorList);
    public void visit(StatementList StatementList);
    public void visit(ConstVarDeclList ConstVarDeclList);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(FormalParamListExtended FormalParamListExtended);
    public void visit(AddopLeft AddopLeft);
    public void visit(Designator Designator);
    public void visit(Term Term);
    public void visit(ConstDeclListExtended ConstDeclListExtended);
    public void visit(AddopRight AddopRight);
    public void visit(ArrayBracks ArrayBracks);
    public void visit(WithNumber WithNumber);
    public void visit(VarDeclListExtended VarDeclListExtended);
    public void visit(VarDeclList VarDeclList);
    public void visit(DesignatorArray DesignatorArray);
    public void visit(FormalParamList FormalParamList);
    public void visit(Expr Expr);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(Statement Statement);
    public void visit(VarDecl VarDecl);
    public void visit(MulopLeft MulopLeft);
    public void visit(Minus Minus);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(MulopRight MulopRight);
    public void visit(FormPars FormPars);
    public void visit(ConstType ConstType);
    public void visit(AddopTermList AddopTermList);
    public void visit(MulopRightModEqual MulopRightModEqual);
    public void visit(MulopRightDivEqual MulopRightDivEqual);
    public void visit(MulopRightMulEqual MulopRightMulEqual);
    public void visit(MulopLeftMod MulopLeftMod);
    public void visit(MulopLeftDiv MulopLeftDiv);
    public void visit(MulopLeftMul MulopLeftMul);
    public void visit(MulopBasicRight MulopBasicRight);
    public void visit(MulopBasicLeft MulopBasicLeft);
    public void visit(AddopRightMinusEqual AddopRightMinusEqual);
    public void visit(AddopRightPlusEqual AddopRightPlusEqual);
    public void visit(AddopLeftPlus AddopLeftPlus);
    public void visit(AddopLeftMinus AddopLeftMinus);
    public void visit(AddopBasicRight AddopBasicRight);
    public void visit(AddopBasicLeft AddopBasicLeft);
    public void visit(AssignopMulopRight AssignopMulopRight);
    public void visit(AssignopAddopRight AssignopAddopRight);
    public void visit(AssignopEqual AssignopEqual);
    public void visit(DesignatorIdentArray DesignatorIdentArray);
    public void visit(DesignatorIdentExtended DesignatorIdentExtended);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(DesignatorFactorExtended DesignatorFactorExtended);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(NewTypeExpr NewTypeExpr);
    public void visit(NewType NewType);
    public void visit(ExprLRPAREN ExprLRPAREN);
    public void visit(ConstTypeVal ConstTypeVal);
    public void visit(NoMulopFactorList NoMulopFactorList);
    public void visit(YesMulopFactorList YesMulopFactorList);
    public void visit(TermFactor TermFactor);
    public void visit(NoAddopTermList NoAddopTermList);
    public void visit(YesAddopTermList YesAddopTermList);
    public void visit(NoMinus NoMinus);
    public void visit(YesMinus YesMinus);
    public void visit(AddExpr AddExpr);
    public void visit(DecDesStatement DecDesStatement);
    public void visit(IncDesStatement IncDesStatement);
    public void visit(EqualDesStatementError EqualDesStatementError);
    public void visit(EqualDesStatement EqualDesStatement);
    public void visit(WithNumberNo WithNumberNo);
    public void visit(WithNumberYes WithNumberYes);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(ReturnStmtNoExpr ReturnStmtNoExpr);
    public void visit(ReturnStmtExpr ReturnStmtExpr);
    public void visit(Assignment Assignment);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(FormalParamListExtendedNo FormalParamListExtendedNo);
    public void visit(FormalParamListExtendedYes FormalParamListExtendedYes);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodVoid MethodVoid);
    public void visit(MethodType MethodType);
    public void visit(VarDeclLIstNo VarDeclLIstNo);
    public void visit(VarDeclListYes VarDeclListYes);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(NoConstDeclListExtended NoConstDeclListExtended);
    public void visit(ConstDeclListExtendedVal ConstDeclListExtendedVal);
    public void visit(ConstChar ConstChar);
    public void visit(ConstBool ConstBool);
    public void visit(ConstNumber ConstNumber);
    public void visit(ConstDecl ConstDecl);
    public void visit(Type Type);
    public void visit(VarDecListExtendedNo VarDecListExtendedNo);
    public void visit(VarDeclErrorList VarDeclErrorList);
    public void visit(VarDecListExtendedYes VarDecListExtendedYes);
    public void visit(NoArray NoArray);
    public void visit(IsArray IsArray);
    public void visit(VarDeclError VarDeclError);
    public void visit(VarDeclBase VarDeclBase);
    public void visit(NoConstVarDecl NoConstVarDecl);
    public void visit(ConstDeclarations ConstDeclarations);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
