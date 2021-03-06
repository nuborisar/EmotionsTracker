package emotionsTracker.asvfactory.com.model;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by nubor on 23/10/2016.
 */
public class DataUtil {

    //todo hayq  guardar un fichero siempre del mismo nombre con losregistros guardados por usuario
//    public static void ArrayList<EmotionTrackModel> saveFileOfTracksModel(Context context,ArrayList<EmotionTrackModel> lstEmotions)
//    {
//        return new ArrayList<EmotionTrackModel>();
//    }
    public static void saveEmotionTrack(Context context, EmotionTrackModel emotionTrackModel) {
        try {
            FileOutputStream fos = context.openFileOutput(emotionTrackModel.getFilename() + ".json", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(emotionTrackModel);
            os.close();
            fos.close();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }

    public static EmotionTrackModel loadEmotionTrack(Context context, String fileName) {
        try {
            FileInputStream fis = context.openFileInput(fileName + ".json");
            ObjectInputStream is = new ObjectInputStream(fis);
            EmotionTrackModel data = (EmotionTrackModel) is.readObject();
            is.close();
            fis.close();
            return data;
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return null;
    }

    /*test*/
    public static <T extends Serializable> void saveSerializable(Context context, T objectToSave, String fileName) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(objectToSave);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a serializable object.
     *
     * @param context  The application context.
     * @param fileName The filename.
     * @param <T>      The object type.
     * @return the serializable object.
     */

    public static <T extends Serializable> T readSerializable(Context context, String fileName) {
        T objectToReturn = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            objectToReturn = (T) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return objectToReturn;
    }

    /**
     * Removes a specified file.
     *
     * @param context  The application context.
     * @param filename The name of the file.
     */

    public static void removeSerializable(Context context, String filename) {
        context.deleteFile(filename);
    }
}
