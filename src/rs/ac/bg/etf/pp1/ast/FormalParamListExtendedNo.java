// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:12


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListExtendedNo extends FormalParamListExtended {

    public FormalParamListExtendedNo () {
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
        buffer.append("FormalParamListExtendedNo(\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListExtendedNo]");
        return buffer.toString();
    }
}
