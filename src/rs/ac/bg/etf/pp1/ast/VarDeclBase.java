// generated with ast extension for cup
// version 0.8
// 18/7/2020 17:2:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclBase extends VarDecl {

    private Type Type;
    private VarDeclListExtended VarDeclListExtended;
    private String varName;
    private ArrayBracks ArrayBracks;

    public VarDeclBase (Type Type, VarDeclListExtended VarDeclListExtended, String varName, ArrayBracks ArrayBracks) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclListExtended=VarDeclListExtended;
        if(VarDeclListExtended!=null) VarDeclListExtended.setParent(this);
        this.varName=varName;
        this.ArrayBracks=ArrayBracks;
        if(ArrayBracks!=null) ArrayBracks.setParent(this);
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

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public ArrayBracks getArrayBracks() {
        return ArrayBracks;
    }

    public void setArrayBracks(ArrayBracks ArrayBracks) {
        this.ArrayBracks=ArrayBracks;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.accept(visitor);
        if(ArrayBracks!=null) ArrayBracks.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseTopDown(visitor);
        if(ArrayBracks!=null) ArrayBracks.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseBottomUp(visitor);
        if(ArrayBracks!=null) ArrayBracks.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclBase(\n");

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

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(ArrayBracks!=null)
            buffer.append(ArrayBracks.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclBase]");
        return buffer.toString();
    }
}
