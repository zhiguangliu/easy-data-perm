package cn.zhgliu.ezdp.finder.impl.http;

import cn.zhgliu.ezdp.finder.DataPermRuleFinder;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ZhgLiu
 */
public class HttpDataPermRuleFinder implements DataPermRuleFinder {

    Logger log = LoggerFactory.getLogger(HttpDataPermRuleFinder.class);

    private String dataPermServer;

    public HttpDataPermRuleFinder( String dataPermServer) {
        this.dataPermServer = dataPermServer;
    }

    @Override
    public List<List<DataPermissionItem>> findRoleGroupedRules(String subSystem, String userId, String operationIdentifier) {

        List<List<DataPermissionItem>> ret = new LinkedList<>();
        String url = dataPermServer + "/api/ecDpApi/findPermissionListGroupByRole/" + subSystem + "/" + userId + "/" + operationIdentifier;
        log.debug("获取规则的地址是：{}", url);

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(1000)
                    .setConnectTimeout(1000)
                    .setSocketTimeout(1000)
                    .build();
            URIBuilder uriBuilder = new URIBuilder(url);
            uriBuilder.addParameter("query", "");
            HttpGet get = new HttpGet(uriBuilder.build());
            get.setConfig(config);
            CloseableHttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                log.debug(result);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ret;
        } finally {

        }
        return ret;
    }


}
