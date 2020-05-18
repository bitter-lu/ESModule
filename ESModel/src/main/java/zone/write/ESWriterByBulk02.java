package zone.write;

import com.atguigu.bean.Stu;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

public class ESWriterByBulk02 {

    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();
        jestClientFactory.setHttpClientConfig(config);

        JestClient jestClient = jestClientFactory.getObject();

        Stu stu1 = new Stu("008", "麻瓜");
        Stu stu2 = new Stu("009", "海格");

        Bulk.Builder builder = new Bulk.Builder();

        Index index1 = new Index.Builder(stu1).id("1008").build();
        Index index2 = new Index.Builder(stu2).id("1009").build();

        builder.defaultIndex("stu_temp_01");
        builder.defaultType("_doc");

        builder.addAction(index1);
        builder.addAction(index2);

        Bulk bulk = builder.build();

        jestClient.execute(bulk);

        jestClient.shutdownClient();
    }
}
