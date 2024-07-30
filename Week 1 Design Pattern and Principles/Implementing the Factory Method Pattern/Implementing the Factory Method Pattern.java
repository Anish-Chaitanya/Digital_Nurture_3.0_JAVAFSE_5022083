// Document.java
// Define the Document interface
public interface Document {
    void open();
    void save();
}
// WordDocument.java
// Implement the WordDocument class
public class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a Word document.");
    }

    @Override
    public void save() {
        System.out.println("Saving a Word document.");
    }
}
// PdfDocument.java
// Implement the PdfDocument class
public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document.");
    }

    @Override
    public void save() {
        System.out.println("Saving a PDF document.");
    }
}
// ExcelDocument.java
// Implement the ExcelDocument class
public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening an Excel document.");
    }

    @Override
    public void save() {
        System.out.println("Saving an Excel document.");
    }
}
// DocumentFactory.java
// Define the abstract factory class
public abstract class DocumentFactory {
    public abstract Document createDocument();
}
// WordDocumentFactory.java
// Implement the WordDocumentFactory class
public class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}
// PdfDocumentFactory.java
// Implement the PdfDocumentFactory class
public class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}
// ExcelDocumentFactory.java
// Implement the ExcelDocumentFactory class
public class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
// FactoryMethodTest.java
// Test the Factory Method Pattern
public class FactoryMethodTest {
    public static void main(String[] args) {
        // Create and use a WordDocument through its factory
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDocument = wordFactory.createDocument();
        wordDocument.open();
        wordDocument.save();

        // Create and use a PdfDocument through its factory
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDocument = pdfFactory.createDocument();
        pdfDocument.open();
        pdfDocument.save();

        // Create and use an ExcelDocument through its factory
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDocument = excelFactory.createDocument();
        excelDocument.open();
        excelDocument.save();
    }
}

