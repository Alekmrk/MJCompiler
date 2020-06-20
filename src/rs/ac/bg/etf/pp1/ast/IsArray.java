// generated with ast extension for cup
// version 0.8
// 20/5/2020 20:6:5


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
