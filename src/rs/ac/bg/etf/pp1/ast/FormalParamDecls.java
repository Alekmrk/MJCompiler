// generated with ast extension for cup
// version 0.8
// 22/5/2020 20:30:15


package rs.ac.bg.etf.pp1.ast;

public class FormalParamDecls extends FormalParamList {

    private FormalParamDecl FormalParamDecl;
    private FormalParamListExtended FormalParamListExtended;

    public FormalParamDecls (FormalParamDecl FormalParamDecl, FormalParamListExtended FormalParamListExtended) {
        this.FormalParamDecl=FormalParamDecl;
        if(FormalParamDecl!=null) FormalParamDecl.setParent(this);
        this.FormalParamListExtended=FormalParamListExtended;
        if(FormalParamListExtended!=null) FormalParamListExtended.setParent(this);
    }

    public FormalParamDecl getFormalParamDecl() {
        return FormalParamDecl;
    }

    public void setFormalParamDecl(FormalParamDecl FormalParamDecl) {
        this.FormalParamDecl=FormalParamDecl;
    }

    public FormalParamListExtended getFormalParamListExtended() {
        return FormalParamListExtended;
    }

    public void setFormalParamListExtended(FormalParamListExtended FormalParamListExtended) {
        this.FormalParamListExtended=FormalParamListExtended;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamDecl!=null) FormalParamDecl.accept(visitor);
        if(FormalParamListExtended!=null) FormalParamListExtended.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamDecl!=null) FormalParamDecl.traverseTopDown(visitor);
        if(FormalParamListExtended!=null) FormalParamListExtended.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamDecl!=null) FormalParamDecl.traverseBottomUp(visitor);
        if(FormalParamListExtended!=null) FormalParamListExtended.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamDecls(\n");

        if(FormalParamDecl!=null)
            buffer.append(FormalParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParamListExtended!=null)
            buffer.append(FormalParamListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamDecls]");
        return buffer.toString();
    }
}
