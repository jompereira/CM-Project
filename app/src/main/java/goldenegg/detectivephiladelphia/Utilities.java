package goldenegg.detectivephiladelphia;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Utilities {



    public static String getDirPath(){
        File baseDir = Environment.getExternalStorageDirectory();
        File bimbyDir = new File(baseDir, "/Apontamentos");

        if (!bimbyDir.exists()) {
            try {
                bimbyDir.mkdirs();
                return bimbyDir.getAbsolutePath();
            } catch (Exception e) {
                return null;
            }
        }else{
            return bimbyDir.getAbsolutePath();
        }

    }

    public static String saveBitmap(Bitmap bm){
        if (bm!=null){
            String dir=getDirPath();
            if (dir!=null){
                // save image code
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

                FileOutputStream fos;
                try {
                    File baseDir= new File(dir);
                    File file=new File(baseDir,"/"+System.currentTimeMillis()+".png");
                    fos = new FileOutputStream(file);
                    baos.writeTo(fos);
                    baos.flush();
                    fos.flush();
                    baos.close();
                    fos.close();
                    return  file.getAbsolutePath();
                } catch (FileNotFoundException e) {
                    //Mudar e acrescentar tag à classe onde vai estar a lista de apontamentos
                  //  Log.d(NoteBook.TAG, e.toString());
                    return null;
                } catch (IOException e) {
                    //Mudar e acrescentar tag à classe onde vai estar a lista de apontamentos
                   // Log.d(NoteBook.TAG, e.toString());
                    return null;
                }
            }
        }
        return null;
    }

    public static Bitmap loadBitmap(String src){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(src, options);

        return bitmap;
    }



}
