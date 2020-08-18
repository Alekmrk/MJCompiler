// generated with ast extension for cup
// version 0.8
// 17/7/2020 19:24:34


package rs.ac.bg.etf.pp1.ast;

public class PrintStmt extends Statement {

    private Expr Expr;
    private WithNumber WithNumber;

    public PrintStmt (Expr Expr, WithNumber WithNumber) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.WithNumber=WithNumber;
        if(WithNumber!=null) WithNumber.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public WithNumber getWithNumber() {
        return WithNumber;
    }

    public void setWithNumber(WithNumber WithNumber) {
        this.WithNumber=WithNumber;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(WithNumber!=null) WithNumber.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(WithNumber!=null) WithNumber.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(WithNumber!=null) WithNumber.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("PrintStmt(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(WithNumber!=null)
            buffer.append(WithNumber.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintStmt]");
        return buffer.toString();
    }
}
