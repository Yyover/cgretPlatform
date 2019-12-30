package com.javaee6.cgret.util;

import com.javaee6.cgret.dao.ClientMapper;
import com.javaee6.cgret.model.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author Yellowyao
 */
public class searchLucene {
   private static Version matchVersion = Version.LUCENE_7_4_0;
   private static Analyzer analyzer = new IKAnalyzer();


    /**
     * <pre>
     *     建立索引
     * </pre>
     */
   public void createIndex(){
       IndexWriter writer = null;
    try {
        // 1.创建Directory (建立在内存or硬盘)
        // 这里选择--->索引建立在硬盘上
        Directory directory = FSDirectory.open(Paths.get("C:/Users/Administrator/Desktop/cgretPlatform/src/main/webapp/resources/index"));

        // 2.创建IndexWriter，来写索引
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        writer = new IndexWriter(directory, iwc);

        // 3.创建Document对象
        Document doc = null;

        // 4.为Document添加Field（Field是Document的一个子元素）（Field是那些大小、内容、标题）
        File f = new File("D:/lucene/example");
        for(File file : f.listFiles()){
            doc = new Document();
            doc.add(new Field("content", new FileReader(file)));
            doc.add(new Field("filename",file));
        }

    }catch (IOException e){
        e.printStackTrace();
    }catch (NullPointerException e){
        e.printStackTrace();
    }
   }

    /**
     * <pre>
     *     索引页面缓冲
     * </pre>
     */
    private  int maxBufferedDocs = 500;

    @Mapper
    public ClientMapper mapper;

    public List<Client> getResult(String queryStr, int id){
        rs = mapper.selectByPrimaryKey(id);

    }
}
