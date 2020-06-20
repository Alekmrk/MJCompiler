// generated with ast extension for cup
// version 0.8
// 20/5/2020 3:25:10


package rs.ac.bg.etf.pp1.ast;

public interface Visitor { 

    public void visit(Designator Designator);
    public void visit(Factor Factor);
    public void visit(Assignop Assignop);
    public void visit(AddopTermList AddopTermList);
    public void visit(Mulop Mulop);
    public void visit(MulopLeft MulopLeft);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(ActualParamList ActualParamList);
    public void visit(Expr Expr);
    public void visit(Minus Minus);
    public void visit(AddopLeft AddopLeft);
    public void visit(FormalParamList FormalParamList);
    public void visit(FormPars FormPars);
    public void visit(VarDeclList VarDeclList);
    public void visit(MulopFactorList MulopFactorList);
    public void visit(Unmatched Unmatched);
    public void visit(Addop Addop);
    public void visit(MethodDeclList MethodDeclList);
    public void visit(Statement Statement);
    public void visit(AddopRight AddopRight);
    public void visit(Term Term);
    public void visit(ConstSectDecl ConstSectDecl);
    public void visit(MulopRight MulopRight);
    public void visit(StatementList StatementList);
    public void visit(Matched Matched);
    public void visit(ActualPars ActualPars);
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
    public void visit(DesignatorIdentExtended DesignatorIdentExtended);
    public void visit(DesignatorIdent DesignatorIdent);
    public void visit(DesignatorFactorExtended DesignatorFactorExtended);
    public void visit(DesignatorFactor DesignatorFactor);
    public void visit(NewTypeExtended NewTypeExtended);
    public void visit(NewType NewType);
    public void visit(ExprLRPAREN ExprLRPAREN);
    public void visit(ConstChar ConstChar);
    public void visit(ConstBool ConstBool);
    public void visit(ConstNumber ConstNumber);
    public void visit(ConstSectDeclNode ConstSectDeclNode);
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
    public void visit(EqualDesStatement EqualDesStatement);
    public void visit(PrintStmt PrintStmt);
    public void visit(ReadStmt ReadStmt);
    public void visit(Assignment Assignment);
    public void visit(NoStmt NoStmt);
    public void visit(Statements Statements);
    public void visit(FormalParamDecl FormalParamDecl);
    public void visit(SingleFormalParamDecl SingleFormalParamDecl);
    public void visit(FormalParamDecls FormalParamDecls);
    public void visit(NoFormParam NoFormParam);
    public void visit(FormParams FormParams);
    public void visit(MethodTypeName MethodTypeName);
    public void visit(MethodDecl MethodDecl);
    public void visit(NoMethodDecl NoMethodDecl);
    public void visit(MethodDeclarations MethodDeclarations);
    public void visit(Type Type);
    public void visit(VarDecl VarDecl);
    public void visit(NoVarDecl NoVarDecl);
    public void visit(VarDeclarations VarDeclarations);
    public void visit(ProgName ProgName);
    public void visit(Program Program);

}
