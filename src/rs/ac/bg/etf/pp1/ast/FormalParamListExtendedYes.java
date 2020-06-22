// generated with ast extension for cup
// version 0.8
// 22/5/2020 14:54:5


package rs.ac.bg.etf.pp1.ast;

public class FormalParamListExtendedYes extends FormalParamListExtended {

    private FormalParamListExtended FormalParamListExtended;
    private FormalParamDecl FormalParamDecl;

    public FormalParamListExtendedYes (FormalParamListExtended FormalParamListExtended, FormalParamDecl FormalParamDecl) {
        this.FormalParamListExtended=FormalParamListExtended;
        if(FormalParamListExtended!=null) FormalParamListExtended.setParent(this);
        this.FormalParamDecl=FormalParamDecl;
        if(FormalParamDecl!=null) FormalParamDecl.setParent(this);
    }

    public FormalParamListExtended getFormalParamListExtended() {
        return FormalParamListExtended;
    }

    public void setFormalParamListExtended(FormalParamListExtended FormalParamListExtended) {
        this.FormalParamListExtended=FormalParamListExtended;
    }

    public FormalParamDecl getFormalParamDecl() {
        return FormalParamDecl;
    }

    public void setFormalParamDecl(FormalParamDecl FormalParamDecl) {
        this.FormalParamDecl=FormalParamDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamListExtended!=null) FormalParamListExtended.accept(visitor);
        if(FormalParamDecl!=null) FormalParamDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamListExtended!=null) FormalParamListExtended.traverseTopDown(visitor);
        if(FormalParamDecl!=null) FormalParamDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamListExtended!=null) FormalParamListExtended.traverseBottomUp(visitor);
        if(FormalParamDecl!=null) FormalParamDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamListExtendedYes(\n");

        if(FormalParamListExtended!=null)
            buffer.append(FormalParamListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParamDecl!=null)
            buffer.append(FormalParamDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamListExtendedYes]");
        return buffer.toString();
    }
}
