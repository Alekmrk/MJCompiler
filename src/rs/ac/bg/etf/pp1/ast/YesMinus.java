// generated with ast extension for cup
// version 0.8
// 22/5/2020 4:9:3


package rs.ac.bg.etf.pp1.ast;

public class YesMinus extends Minus {

    public YesMinus () {
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
        buffer.append("YesMinus(\n");

        buffer.append(tab);
        buffer.append(") [YesMinus]");
        return buffer.toString();
    }
}
