// generated with ast extension for cup
// version 0.8
// 15/7/2020 22:50:39


package rs.ac.bg.etf.pp1.ast;

public class IsArray extends ArrayBracks {

    public IsArray () {
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
        buffer.append("IsArray(\n");

        buffer.append(tab);
        buffer.append(") [IsArray]");
        return buffer.toString();
    }
}
