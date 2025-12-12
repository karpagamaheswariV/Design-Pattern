public class Main
{
    public static void main(String[] args) {

        TextRender text = new Plaintext("Hello World");

        TextRender boldText = new BoldDecorator(text);
        TextRender italicText = new ItalicDecorator(text);

        // Applying both (nested)
        TextRender boldItalic = new BoldDecorator(new ItalicDecorator(text));

        System.out.println("Bold: " + boldText.render());
        System.out.println("Italic: " + italicText.render());
        System.out.println("Bold + Italic: " + boldItalic.render());
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
