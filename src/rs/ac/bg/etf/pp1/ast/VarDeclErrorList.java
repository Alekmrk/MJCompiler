// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclErrorList extends VarDeclListExtended {

    private VarDeclListExtended VarDeclListExtended;

    public VarDeclErrorList (VarDeclListExtended VarDeclListExtended) {
        this.VarDeclListExtended=VarDeclListExtended;
        if(VarDeclListExtended!=null) VarDeclListExtended.setParent(this);
    }

    public VarDeclListExtended getVarDeclListExtended() {
        return VarDeclListExtended;
    }

    public void setVarDeclListExtended(VarDeclListExtended VarDeclListExtended) {
        this.VarDeclListExtended=VarDeclListExtended;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListExtended!=null) VarDeclListExtended.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclErrorList(\n");

        if(VarDeclListExtended!=null)
            buffer.append(VarDeclListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclErrorList]");
        return buffer.toString();
    }
}
