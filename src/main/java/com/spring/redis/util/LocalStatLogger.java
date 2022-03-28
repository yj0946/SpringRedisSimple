package com.spring.redis.util;

import static com.alibaba.druid.util.JdbcSqlStatUtils.rtrim;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.PropertyConfigurator;

import com.alibaba.druid.pool.DruidDataSourceStatLogger;
import com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter;
import com.alibaba.druid.pool.DruidDataSourceStatValue;
import com.alibaba.druid.stat.JdbcSqlStatValue;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;

/**
 * @author WangHuijie<wanghuijie@carelink.cn>
 */
public class LocalStatLogger extends DruidDataSourceStatLoggerAdapter implements DruidDataSourceStatLogger{
    private static final Log LOGGER = LogFactory.getLog(LocalStatLogger.class);

    /**
     * @see com.alibaba.druid.pool.DruidDataSourceStatLoggerAdapter#log(com.alibaba.druid.pool.DruidDataSourceStatValue)
     */
    @Override
    public void log(DruidDataSourceStatValue statValue) {
        Map<String, Object> map = new LinkedHashMap<>();

        /*map.put("url", statValue.getUrl());
        map.put("dbType", statValue.getDbType());*/
        map.put("name", statValue.getName());
        map.put("activeCount", statValue.getActiveCount());

        if (statValue.getActivePeak() > 0) {
            map.put("activePeak", statValue.getActivePeak());
            map.put("activePeakTime", statValue.getActivePeakTime());
        }
        map.put("poolingCount", statValue.getPoolingCount());
        if (statValue.getPoolingPeak() > 0) {
            map.put("poolingPeak", statValue.getPoolingPeak());
            map.put("poolingPeakTime", statValue.getPoolingPeakTime());
        }

        map.put("connectCount", statValue.getConnectCount());
        map.put("closeCount", statValue.getCloseCount());

        if (statValue.getWaitThreadCount() > 0) {
            map.put("waitThreadCount", statValue.getWaitThreadCount());
        }

        if (statValue.getNotEmptyWaitCount() > 0) {
            map.put("notEmptyWaitCount", statValue.getNotEmptyWaitCount());
        }

        if (statValue.getNotEmptyWaitMillis() > 0) {
            map.put("notEmptyWaitMillis", statValue.getNotEmptyWaitMillis());
        }

        if (statValue.getLogicConnectErrorCount() > 0) {
            map.put("logicConnectErrorCount", statValue.getLogicConnectErrorCount());
        }

        if (statValue.getPhysicalConnectCount() > 0) {
            map.put("physicalConnectCount", statValue.getPhysicalConnectCount());
        }

        if (statValue.getPhysicalCloseCount() > 0) {
            map.put("physicalCloseCount", statValue.getPhysicalCloseCount());
        }

        if (statValue.getPhysicalConnectErrorCount() > 0) {
            map.put("physicalConnectErrorCount", statValue.getPhysicalConnectErrorCount());
        }

        if (statValue.getExecuteCount() > 0) {
            map.put("executeCount", statValue.getExecuteCount());
        }

        if (statValue.getErrorCount() > 0) {
            map.put("errorCount", statValue.getErrorCount());
        }

        if (statValue.getCommitCount() > 0) {
            map.put("commitCount", statValue.getCommitCount());
        }

        if (statValue.getRollbackCount() > 0) {
            map.put("rollbackCount", statValue.getRollbackCount());
        }

        if (statValue.getPstmtCacheHitCount() > 0) {
            map.put("pstmtCacheHitCount", statValue.getPstmtCacheHitCount());
        }

        if (statValue.getPstmtCacheMissCount() > 0) {
            map.put("pstmtCacheMissCount", statValue.getPstmtCacheMissCount());
        }

        if (statValue.getStartTransactionCount() > 0) {
            map.put("startTransactionCount", statValue.getStartTransactionCount());
            map.put("transactionHistogram", rtrim(statValue.getTransactionHistogram()));
        }

        if (statValue.getConnectCount() > 0) {
            map.put("connectionHoldTimeHistogram", rtrim(statValue.getConnectionHoldTimeHistogram()));
        }

        if (statValue.getClobOpenCount() > 0) {
            map.put("clobOpenCount", statValue.getClobOpenCount());
        }

        if (statValue.getBlobOpenCount() > 0) {
            map.put("blobOpenCount", statValue.getBlobOpenCount());
        }

        if (statValue.getSqlSkipCount() > 0) {
            map.put("sqlSkipCount", statValue.getSqlSkipCount());
        }

        ArrayList<Map<String, Object>> sqlList = new ArrayList<Map<String, Object>>();
        if (statValue.getSqlList().size() > 0) {
            for (JdbcSqlStatValue sqlStat : statValue.getSqlList()) {
                Map<String, Object> sqlStatMap = new LinkedHashMap<String, Object>();
                sqlStatMap.put("sql", sqlStat.getSql());

                if (sqlStat.getExecuteCount() > 0) {
                    sqlStatMap.put("executeCount", sqlStat.getExecuteCount());
                    sqlStatMap.put("executeMillisMax", sqlStat.getExecuteMillisMax());
                    sqlStatMap.put("executeMillisTotal", sqlStat.getExecuteMillisTotal());

                    sqlStatMap.put("executeHistogram", rtrim(sqlStat.getExecuteHistogram()));
                    sqlStatMap.put("executeAndResultHoldHistogram", rtrim(sqlStat.getExecuteAndResultHoldHistogram()));
                }

                long executeErrorCount = sqlStat.getExecuteErrorCount();
                if (executeErrorCount > 0) {
                    sqlStatMap.put("executeErrorCount", executeErrorCount);
                }

                int runningCount = sqlStat.getRunningCount();
                if (runningCount > 0) {
                    sqlStatMap.put("runningCount", runningCount);
                }

                int concurrentMax = sqlStat.getConcurrentMax();
                if (concurrentMax > 0) {
                    sqlStatMap.put("concurrentMax", concurrentMax);
                }

                if (sqlStat.getFetchRowCount() > 0) {
                    sqlStatMap.put("fetchRowCount", sqlStat.getFetchRowCount());
                    sqlStatMap.put("fetchRowCount", sqlStat.getFetchRowCountMax());
                    sqlStatMap.put("fetchRowHistogram", rtrim(sqlStat.getFetchRowHistogram()));
                }

                if (sqlStat.getUpdateCount() > 0) {
                    sqlStatMap.put("updateCount", sqlStat.getUpdateCount());
                    sqlStatMap.put("updateCountMax", sqlStat.getUpdateCountMax());
                    sqlStatMap.put("updateHistogram", rtrim(sqlStat.getUpdateHistogram()));
                }

                if (sqlStat.getInTransactionCount() > 0) {
                    sqlStatMap.put("inTransactionCount", sqlStat.getInTransactionCount());
                }

                if (sqlStat.getClobOpenCount() > 0) {
                    sqlStatMap.put("clobOpenCount", sqlStat.getClobOpenCount());
                }

                if (sqlStat.getBlobOpenCount() > 0) {
                    sqlStatMap.put("blobOpenCount", sqlStat.getBlobOpenCount());
                }

                sqlList.add(sqlStatMap);
            }

            map.put("sqlList", sqlList);
        }

        String text = JSONUtils.toJSONString(map);

        beforeLog();
        LOGGER.info(text);
        afterLog();
    }

    /**
     * 在记录LOG前指定.properties文件
     */
    private void beforeLog() {
        propertyConfigure("/druid_log4j.properties");
    }

    /**
     * 在记录LOG后恢复指定本来的.properties文件
     */
    private void afterLog() {

        propertyConfigure("/log4j.properties");
    }

    /**
     * 指定.properties文件
     * @param filePath
     */
    private void propertyConfigure(String filePath) {

        URL resource = getClass().getResource(filePath);
        PropertyConfigurator.configure(resource);
    }

}
