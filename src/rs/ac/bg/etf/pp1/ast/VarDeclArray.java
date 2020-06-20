// generated with ast extension for cup
// version 0.8
// 20/5/2020 20:6:5


package rs.ac.bg.etf.pp1.ast;

public class VarDeclArray extends VarDecl {

    private Type Type;
    private String varName;
    private ArrayBracks ArrayBracks;
    private VarDeclListExtended VarDeclListExtended;

    public VarDeclArray (Type Type, String varName, ArrayBracks ArrayBracks, VarDeclListExtended VarDeclListExtended) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.ArrayBracks=ArrayBracks;
        if(ArrayBracks!=null) ArrayBracks.setParent(this);
        this.VarDeclListExtended=VarDeclListExtended;
        if(VarDeclListExtended!=null) VarDeclListExtended.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
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
        if(ArrayBracks!=null) ArrayBracks.accept(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ArrayBracks!=null) ArrayBracks.traverseTopDown(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ArrayBracks!=null) ArrayBracks.traverseBottomUp(visitor);
        if(VarDeclListExtended!=null) VarDeclListExtended.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclArray(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
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

        if(VarDeclListExtended!=null)
            buffer.append(VarDeclListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclArray]");
        return buffer.toString();
    }
}
