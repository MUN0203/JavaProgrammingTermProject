import javax.swing.SwingUtilities;

public class App {
    // MVC 모델
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
