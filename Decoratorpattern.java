public class Main
{
    public static void main(String[] args) {

        TextRender text = new Plaintext("Hello World");

        TextRender boldText = new BoldDecorator(text);
        TextRender italicText = new ItalicDecorator(text);
        TextRender underlineText = new UnderlineDecorator(text);
        // Applying both (nested)
        TextRender biu = new BoldDecorator(new ItalicDecorator(new UnderlineDecorator(text)));

        System.out.println("Bold: " + boldText.render());
        System.out.println("Italic: " + italicText.render());
        System.out.println("UnderLine: " + underlineText.render());
         System.out.println(" \n" + biu.render());
    }
}

interface TextRender {
    String render();
}

class Plaintext implements TextRender {
    String text;

    Plaintext(String text) {
        this.text = text;
    }

    public String render() {
        return text;
    }
}

abstract class TextDecoder implements TextRender {
    protected TextRender textrender;

    TextDecoder(TextRender textrender) {
        this.textrender = textrender;
    }
}

class BoldDecorator extends TextDecoder {

    BoldDecorator(TextRender textrender) {
        super(textrender);
    }

    public String render() {
        return "<b>" + textrender.render() + "</b>";
    }
}

class ItalicDecorator extends TextDecoder {

    ItalicDecorator(TextRender textrender) {
        super(textrender);
    }

    public String render() {
        return "<i>" + textrender.render() + "</i>";
    }
}
class UnderlineDecorator extends TextDecoder {

    UnderlineDecorator(TextRender textrender) {
        super(textrender);
    }

    public String render() {
        return "<u>" + textrender.render() + "</u>";
    }
}

