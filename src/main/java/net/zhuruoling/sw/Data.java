package net.zhuruoling.sw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Data {

    public static final Data instace = new Data();
    private List<WhitelistData> whitelistDatas = new ArrayList<>();
    private Path configPath = Path.of("./config");
    private Path jsonPath = configPath.resolve("shared_whitelist.json");
    private Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

    void load() {
        configPath.toFile().mkdirs();
        if (!jsonPath.toFile().exists()) {
            try {
                jsonPath.toFile().createNewFile();
                FileWriter writer = new FileWriter(jsonPath.toFile());
                var stor = new Storage(new ArrayList());
                gson.toJson(stor, writer);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            whitelistDatas.clear();
            FileReader writer = new FileReader(jsonPath.toFile());
            var obj = gson.fromJson(writer, Storage.class);
            whitelistDatas.addAll(obj.dataList);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    void save() {
        try {
            if (jsonPath.toFile().exists()) {
                jsonPath.toFile().delete();
                jsonPath.toFile().createNewFile();
                FileWriter writer = new FileWriter(jsonPath.toFile());
                gson.toJson(new Storage(whitelistDatas), writer);
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WhitelistData> getWhitelistDatas() {
        return whitelistDatas;
    }

    static class Storage {
        List<WhitelistData> dataList;

        public Storage(List<WhitelistData> whitelistDatas) {
            this.dataList = whitelistDatas;
        }
    }

}
