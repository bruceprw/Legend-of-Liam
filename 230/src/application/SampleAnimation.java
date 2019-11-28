package application;

import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SampleAnimation extends Application {
	//private final Image IMAGE = new Image(getClass().getResourceAsStream("1.png"));
    private final Image IMAGE = new Image(getClass().getResourceAsStream("1116010303.png"));          //(9 54 0 0 64 64)
    //private final Image IMAGE = new Image(getClass().getResourceAsStream("1116010303flipped.png")); //(9 54 0 0 64 64)
	//private final Image IMAGE = new Image(getClass().getResourceAsStream("blueKey1.png"));          //(5 5 0 0 32 32)
	//private final Image IMAGE = new Image(getClass().getResourceAsStream("MonedaD.png"));
	//private final Image IMAGE = new Image(getClass().getResourceAsStream("Potion1.png"));
	//private final Image IMAGE = new Image(getClass().getResourceAsStream("treasureChest-Sheet.png"));
	
	//total number of columns of the object in the photo
    private static final int COLUMNS  = 9;  //3  9
    //count how many times the animation is
    private static final int COUNT    = 54;  //3  9
    //move the view point of x axis (positive no. RIGHT & negative no. LEFT)
    private static final int OFFSET_X = 0;  //0  0
    //move the view point of y axis (positive no. UP & negative no. DOWN)
    private static final int OFFSET_Y = 0; //32  64
    //width of the single object in the photo in pixel (Not the whole photo with many object)
    private static final int WIDTH    = 64; //32  64
    //height of the single object in the photo in pixel (Not the whole photo with many form)
    private static final int HEIGHT   = 64; //32  64

    @Override
    public void start(Stage primaryStage) {
        final ImageView imageView = new ImageView(IMAGE);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setViewport(new Rectangle2D(OFFSET_X, OFFSET_Y, WIDTH, HEIGHT));
        final Animation animation = new SpriteAnimation(imageView, Duration.millis(5000), COUNT, COLUMNS, OFFSET_X, OFFSET_Y, WIDTH, HEIGHT);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        primaryStage.setScene(new Scene(new Group(imageView),300,300));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private class SpriteAnimation extends Transition {
        private final ImageView imageView;
        private final int count;
        private final int columns;
        private final int offsetX;
        private final int offsetY;
        private final int width;
        private final int height;

        public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int offsetX, int offsetY, int width, int height) {
            this.imageView = imageView;
            this.count = count;
            this.columns = columns;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
            this.width = width;
            this.height = height;
            setCycleDuration(duration);
        }
        protected void interpolate(double k) {
            final int index = Math.min((int) Math.floor(k * count), count - 1);
            final int x = (index % columns) * width + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
        }
    }
}