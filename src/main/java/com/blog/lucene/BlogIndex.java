package com.blog.lucene;

import com.blog.domain.Blog;
import com.blog.utils.DateUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.StringReader;
import java.nio.file.Paths;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用Lucene对博客进行增删改查
 */
public class BlogIndex {
    private Directory directory = null;//定义一个目录
    private String lucenePath="E:\\Lucene\\Dir_blog";//定义lucene的存放路径

    /**
     * 获取对lucene的写入方法
     */
    private IndexWriter getWrite() throws Exception {
        //创建分词器对象
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        //创建Directory 声明索引库的位置
        directory = FSDirectory.open(Paths.get(lucenePath,new String[0]));
        //创建IndexWriterConfig 声明索引库需要的配置
        IndexWriterConfig con = new IndexWriterConfig(analyzer);
        //创建IndexWriter写入对象
        IndexWriter writer = new IndexWriter(directory, con);
        return  writer;
    }
    /**
     * 增加索引
     */
    public void addIndex(Blog blog) throws Exception {
        IndexWriter writer = getWrite();
        Document document = new Document();
        document.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title", blog.getTitle(),Field.Store.YES));
        document.add(new StringField("releaseDate", DateUtil.formatDate(new Date(),"yyyy-MM-dd"), Field.Store.YES));
        document.add(new TextField("content",blog.getContent(),Field.Store.YES));
        document.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
        writer.addDocument(document);
        writer.close();
    }

    /**
     * 更新索引
     * @param blog
     * @throws Exception
     */
    public void updateIndex(Blog blog) throws Exception{
        IndexWriter writer = getWrite();
        Document document = new Document();
        document.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        document.add(new TextField("title", blog.getTitle(),Field.Store.YES));
        document.add(new StringField("releaseDate", DateUtil.formatDate(new Date(),"yyyy-MM-dd"), Field.Store.YES));
        document.add(new TextField("content",blog.getContent(),Field.Store.YES));
        document.add(new TextField("keyWord",blog.getKeyWord(),Field.Store.YES));
        writer.updateDocument(new Term("id",String.valueOf(blog.getId())),document);
        writer.close();
    }
    /***
     * 删除索引
     * @param blogId
     * @throws Exception
     */
    public void  deleteIndex(String blogId) throws Exception{
        IndexWriter writer = getWrite();
        /**
         * writer.deleteDocuments
         * 并不是真正的删除 只是将删除的索引放到.del中
         * 相当于window的垃圾回收站
         */
        writer.deleteDocuments(new Term[]{new Term("id",blogId)});
       /**
        * 完全删除索引
        forceMergeDeletes()方法是对已经执行deleteDocuments()的索引进行删除
        完全删除是不可恢复的
        */
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }

    public List<Blog> searchBlog(String q)throws Exception{
        List<Blog> list = new LinkedList<>();
        FSDirectory.open(Paths.get(lucenePath,new String[0]));
        //获取reader
        IndexReader reader = DirectoryReader.open(directory);
        //获取流
        IndexSearcher searcher = new IndexSearcher(reader);
        //放入查询条件
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        QueryParser queryParser = new QueryParser("title", analyzer);
        Query parse = queryParser.parse(q);
        QueryParser queryParser2 = new QueryParser("content", analyzer);
        Query parse2 = queryParser2.parse(q);
        QueryParser queryParser3 = new QueryParser("keyWord", analyzer);
        Query parse3 = queryParser3.parse(q);
        builder.add(parse, BooleanClause.Occur.SHOULD);
        builder.add(parse2,BooleanClause.Occur.SHOULD);
        builder.add(parse3,BooleanClause.Occur.SHOULD);
        //查询结果  最多返回100条数据
        TopDocs hits = searcher.search(builder.build(), 100);
        //高亮搜索字显示
        QueryScorer queryScorer = new QueryScorer(parse);
        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>","</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,queryScorer);
        highlighter.setTextFragmenter(fragmenter);
        //遍历结果 放入list中
        for (ScoreDoc scoreDoc:hits.scoreDocs){
            Document document = searcher.doc(scoreDoc.doc);
            Blog blog = new Blog();
            blog.setId(Integer.parseInt(document.get("id")));
            blog.setReleaseDateStr(document.get("releaseDate"));
            String title = document.get("title");
            //因为是html 所以进行转义
            String content = StringEscapeUtils.escapeHtml(document.get("content"));
            String keyWord = document.get("keyWord");
            if(title!=null){
                TokenStream tokenStream = analyzer.tokenStream("title", new StringReader(title));
                String hTitle = highlighter.getBestFragment(tokenStream, title);
                if(StringUtils.isEmpty(hTitle)){
                    blog.setTitle(title);
                }else {
                    blog.setTitle(hTitle);
                }
            }
            if(content!=null){
                TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(content));
                String hContent = highlighter.getBestFragment(tokenStream, content);
                if(StringUtils.isEmpty(hContent)){
                    if(content.length()<=200){
                        blog.setContent(content);
                    }else {
                        blog.setContent(content.substring(0,200));
                    }
                }else {
                    blog.setContent(hContent);
                }
            }
            if(keyWord!=null){
                TokenStream tokenStream = analyzer.tokenStream("keyWord", new StringReader(keyWord));
                String hkeyWord = highlighter.getBestFragment(tokenStream, keyWord);
                if(StringUtils.isEmpty(hkeyWord)){
                        blog.setContent(keyWord);
                }else {
                    blog.setContent(hkeyWord);
                }
            }
            list.add(blog);
        }
        return list;
    }
}
