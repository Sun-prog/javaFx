package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    public TextField carrot;
    public TableView<Result> resultTable;
    public int tryCount = 78;
   // ArrayList<String> wordResult;
   List<String> wordResult = new ArrayList<String>();

    public void go(javafx.event.ActionEvent actionEvent) {
        Result result = new Result();
        String carrots = carrot.getText();
        //System.out.println("go"+guess);
        result.setCarrot(carrots);
        wordResult.add(carrots);
        resultTable.getItems().add(result);
        result.setFish(tryCount);
    }

    public void word(ActionEvent actionEvent) {
        try {
            // создаем модель docx документа,
            // к которой будем прикручивать наполнение (колонтитулы, текст)

            XWPFDocument docxModel = new XWPFDocument();
            CTSectPr ctSectPr = docxModel.getDocument().getBody().addNewSectPr();
            // получаем экземпляр XWPFHeaderFooterPolicy для работы с колонтитулами
            XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(docxModel, ctSectPr);

            // создаем верхний колонтитул Word файла
            CTP ctpHeaderModel = createHeaderModel(
                    "Верхний колонтитул - создано с помощью Apache POI на Java :)"
            );
            // устанавливаем сформированный верхний
            // колонтитул в модель документа Word
            XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeaderModel, docxModel);
            headerFooterPolicy.createHeader(
                    XWPFHeaderFooterPolicy.DEFAULT,
                    new XWPFParagraph[]{headerParagraph}
            );

            // создаем нижний колонтитул docx файла
            CTP ctpFooterModel = createFooterModel("Просто нижний колонтитул");
            // устанавливаем сформированый нижний
            // колонтитул в модель документа Word
            XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooterModel, docxModel);
            headerFooterPolicy.createFooter(
                    XWPFHeaderFooterPolicy.DEFAULT,
                    new XWPFParagraph[]{footerParagraph}
            );

            // создаем обычный параграф, который будет расположен слева,
            // будет синим курсивом со шрифтом 25 размера
            XWPFParagraph bodyParagraph = docxModel.createParagraph();
            bodyParagraph.setAlignment(ParagraphAlignment.RIGHT);
            XWPFRun paragraphConfig = bodyParagraph.createRun();
            paragraphConfig.setItalic(true);
            paragraphConfig.setFontSize(25);
            // HEX цвет без решетки #
            paragraphConfig.setColor("06357a");

            String itogWord = "Поздравляем! Осталось только сварить суп. Итого вы заказали ";
            for (String name : wordResult) {
                itogWord = itogWord + name + " шт. моркови и еще " + System.lineSeparator();
            }
            paragraphConfig.setText(
                    itogWord +"Суперсуп для кота. Подписывайтесь на наш ютуб канал!"
            );

            // сохраняем модель docx документа в файл
            FileOutputStream outputStream = new FileOutputStream("C:/Users/grupp/Documents/study/Apache POI Word Test.docx");
            docxModel.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Успешно записан в файл");
    }

    private static CTP createFooterModel(String footerContent) {
        // создаем футер или нижний колонтитул
        CTP ctpFooterModel = CTP.Factory.newInstance();
        CTR ctrFooterModel = ctpFooterModel.addNewR();
        CTText cttFooter = ctrFooterModel.addNewT();

        cttFooter.setStringValue(footerContent);
        return ctpFooterModel;
    }

    private static CTP createHeaderModel(String headerContent) {
        // создаем хедер или верхний колонтитул
        CTP ctpHeaderModel = CTP.Factory.newInstance();
        CTR ctrHeaderModel = ctpHeaderModel.addNewR();
        CTText cttHeader = ctrHeaderModel.addNewT();

        cttHeader.setStringValue(headerContent);
        return ctpHeaderModel;

    }


}
