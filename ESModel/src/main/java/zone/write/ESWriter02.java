package zone.write;

import com.sun.org.apache.bcel.internal.generic.NEW;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;
import zone.bean.Stu;

import java.io.IOException;

public class ESWriter02 {
    public static void main(String[] args) throws IOException {

        JestClientFactory jestClientFactory = new JestClientFactory();

        HttpClientConfig config = new HttpClientConfig.Builder("http://hadoop102:9200").build();

        jestClientFactory.setHttpClientConfig(config);

        JestClient jestClient = jestClientFactory.getObject();

        Stu stu = new Stu("004", "少爷");
        Index index = new Index.Builder(stu)
                .index("stu_temp_01")
                .type("_doc")
                .id("1004")
                .build();

        jestClient.execute(index);

        jestClient.shutdownClient();
    }
}
