// generated with ast extension for cup
// version 0.8
// 20/5/2020 20:29:31


package rs.ac.bg.etf.pp1.ast;

public class MulopLeftMod extends MulopLeft {

    public MulopLeftMod () {
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
        buffer.append("MulopLeftMod(\n");

        buffer.append(tab);
        buffer.append(") [MulopLeftMod]");
        return buffer.toString();
    }
}
