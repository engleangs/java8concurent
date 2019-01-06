package com.demo.knn;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Data {

    private String path;
    private static class DataInner{
        private static Data INSTANCE = new Data();
    }
    public static Data getInstance(){
        return DataInner.INSTANCE;
    }
    private Data()
    {

    }

    public void setPath(String path) {
        this.path = path;
    }
    public List<BankData> load() throws IOException {
        Stream<String> lines = Files.lines(Paths.get(path), Charset.defaultCharset());
       return lines.filter(it->!it.equals("")).map(item-> new BankData().parse(item)).collect(Collectors.toList());
    }

}
