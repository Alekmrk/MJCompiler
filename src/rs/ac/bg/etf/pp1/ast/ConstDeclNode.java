// generated with ast extension for cup
// version 0.8
// 21/5/2020 14:48:17


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclNode extends ConstDecl {

    private Type Type;
    private String I2;
    private ConstType ConstType;
    private ConstDeclListExtended ConstDeclListExtended;

    public ConstDeclNode (Type Type, String I2, ConstType ConstType, ConstDeclListExtended ConstDeclListExtended) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.I2=I2;
        this.ConstType=ConstType;
        if(ConstType!=null) ConstType.setParent(this);
        this.ConstDeclListExtended=ConstDeclListExtended;
        if(ConstDeclListExtended!=null) ConstDeclListExtended.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getI2() {
        return I2;
    }

    public void setI2(String I2) {
        this.I2=I2;
    }

    public ConstType getConstType() {
        return ConstType;
    }

    public void setConstType(ConstType ConstType) {
        this.ConstType=ConstType;
    }

    public ConstDeclListExtended getConstDeclListExtended() {
        return ConstDeclListExtended;
    }

    public void setConstDeclListExtended(ConstDeclListExtended ConstDeclListExtended) {
        this.ConstDeclListExtended=ConstDeclListExtended;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstType!=null) ConstType.accept(visitor);
        if(ConstDeclListExtended!=null) ConstDeclListExtended.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstType!=null) ConstType.traverseTopDown(visitor);
        if(ConstDeclListExtended!=null) ConstDeclListExtended.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstType!=null) ConstType.traverseBottomUp(visitor);
        if(ConstDeclListExtended!=null) ConstDeclListExtended.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclNode(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+I2);
        buffer.append("\n");

        if(ConstType!=null)
            buffer.append(ConstType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclListExtended!=null)
            buffer.append(ConstDeclListExtended.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclNode]");
        return buffer.toString();
    }
}
