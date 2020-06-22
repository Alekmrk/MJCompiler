package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.CounterVisitor.VarCounter;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;

public class CodeGenerator extends VisitorAdaptor {

    private int varCount;

    private int paramCnt;

    private int mainPc;

    public int getMainPc() {
        return mainPc;
    }

    @Override
    public void visit(MethodType methodType) {
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
    public void visit(VarDecl VarDecl) {
        varCount++;
    }

    @Override
    public void visit(FormalParamDecl FormalParam) {
        paramCnt++;
    }

    @Override
    public void visit(MethodDecl MethodDecl) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    @Override
    public void visit(ReturnStmtExpr returnStmtExpr) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }

    @Override
    public void visit(ReturnStmtNoExpr returnStmtNoExpr) {
        Code.put(Code.exit);
        Code.put(Code.return_);
    }
}
/*
    @Override
    public void visit(Assignment Assignment) {
        Code.store(Assignment.getDesignator().obj);
    }
    @Override
    public void visit(ConstType constType) {
        //Code.load(new Obj(Obj.Con, "$", constType.obj.getType(), constType.g, 0));
    }

    @Override
    public void visit(Designator Designator) {
        SyntaxNode parent = Designator.getParent();
        if (Assignment.class != parent.getClass() && FuncCall.class != parent.getClass()) {
            Code.load(Designator.obj);
        }
    }

    @Override
    public void visit(FuncCall FuncCall) {
        Obj functionObj = FuncCall.getDesignator().obj;
        int offset = functionObj.getAdr() - Code.pc;
        Code.put(Code.call);
        Code.put2(offset);
    }

    @Override
    public void visit(PrintStmt PrintStmt) {
        Code.put(Code.const_5);
        Code.put(Code.print);
    }

    @Override
    public void visit(AddExpr AddExpr) {
        Code.put(Code.add);
    }
}*/
