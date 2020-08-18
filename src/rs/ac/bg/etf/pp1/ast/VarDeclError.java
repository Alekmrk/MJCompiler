// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclError extends VarDecl {

    private Type Type;
    private VarDeclListExtended VarDeclListExtended;

    public VarDeclError (Type Type, VarDeclListExtended VarDeclListExtended) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclListExtended=VarDeclListExtended;
        if(VarDeclListExtended!=null) VarDeclListExtended.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(Type!=null) Type.accept(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclError(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListExtended!=null)
            buffer.append(VarDeclListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclError]");
        return buffer.toString();
    }
}
