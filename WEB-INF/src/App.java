import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class App {
    public static void main(String[] args) {
        // Charger la bibliothèque native d'OpenCV
        System.loadLibrary("opencv_java");

        // Lire une image depuis le fichier
        Mat image = Highgui.imread("chemin/vers/votre/image.jpg");

        // Vérifier si l'image a été chargée avec succès
        if (image.empty()) {
            System.out.println("Impossible de charger l'image");
            return;
        }

        // Effectuer des opérations sur l'image (par exemple, convertir en niveaux de gris)
        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);

        // Enregistrer l'image modifiée
        Highgui.imwrite("chemin/vers/votre/image_grayscale.jpg", image);
    }
}
