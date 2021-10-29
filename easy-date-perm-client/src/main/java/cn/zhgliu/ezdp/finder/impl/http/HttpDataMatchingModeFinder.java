package cn.zhgliu.ezdp.finder.impl.http;

import cn.hutool.json.JSONUtil;
import cn.zhgliu.ezdp.consts.ParamNames;
import cn.zhgliu.ezdp.finder.DataPermMatchingModeFinder;
import cn.zhgliu.ezdp.model.DataPermissionBaseInfo;
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

/**
 * @author ZhgLiu
 */
public class HttpDataMatchingModeFinder implements DataPermMatchingModeFinder {

    Logger log = LoggerFactory.getLogger(HttpDataMatchingModeFinder.class);

    private String dataPermServer;

    public HttpDataMatchingModeFinder(String dataPermServer) {
        this.dataPermServer = dataPermServer;
    }

    public static final String MATCHING_MODE_URL = "/api/matchingMode";

    @Override
    public DataPermissionBaseInfo findMatchingMode(String subSystem, String operationIdentifier) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(1000)
                    .setConnectTimeout(1000)
                    .setSocketTimeout(1000)
                    .build();
            URIBuilder uriBuilder = new URIBuilder(dataPermServer + MATCHING_MODE_URL);
            uriBuilder.addParameter(ParamNames.SUBSYSTEM, subSystem);
            uriBuilder.addParameter(ParamNames.OPERATION_IDENTIFIER, operationIdentifier);
            HttpGet get = new HttpGet(uriBuilder.build());
            get.setConfig(config);
            CloseableHttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "utf-8");
                log.debug(result);
                DataPermissionBaseInfo dataPermissionBaseInfo = JSONUtil.parseObj(result).toBean(DataPermissionBaseInfo.class);
                return dataPermissionBaseInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
