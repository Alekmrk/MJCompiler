// generated with ast extension for cup
// version 0.8
// 21/5/2020 15:23:18


package rs.ac.bg.etf.pp1.ast;

public class VarDecListExtendedNo extends VarDeclListExtended {

    public VarDecListExtendedNo () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecListExtendedNo(\n");

        buffer.append(tab);
        buffer.append(") [VarDecListExtendedNo]");
        return buffer.toString();
    }
}
