import javax.swing.SwingUtilities;

public class ProgramLauncher {
    public static void main(String[] args) {
        //System.out.println("TEST");
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainWindow main = new MainWindow();
                main.show();
                
            }
        });
    }
}
