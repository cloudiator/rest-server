package io.github.cloudiator.rest;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import io.github.cloudiator.rest.api.ApiException;
import io.github.cloudiator.rest.model.LRRStatus;
import io.github.cloudiator.rest.model.LRRType;
import io.github.cloudiator.rest.model.LongRunningRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Nullable;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class LRRMapService {

    /**
     * userId, (llrId, LongRunningTask)
     */
    private final HashMap<String, HashMap<String, LongRunningRequest>> table;
    private IDGenerator IDGen = new IDGenerator();

    public LRRMapService() {
        this.table = new HashMap<String, HashMap<String, LongRunningRequest>>();
    }

    @Nullable
    public LongRunningRequest getLRR(String userId, String taskId) {

        checkNotNull(userId, "userId is null");
        checkNotNull(taskId, "taskId is null");

        checkArgument(!userId.isEmpty(), "userID is empty");
        checkArgument(!taskId.isEmpty());

        return table.get(userId).get(taskId);
    }

    public LongRunningRequest addLRR(String userId, String vmRequest) {

        checkNotNull(userId, "userId is null");
        checkArgument(!userId.isEmpty(), "userID is empty");

        HashMap<String, LongRunningRequest> map;
        if (table.get(userId) == null) {
            map = new HashMap<String, LongRunningRequest>();
            table.put(userId, map);
        } else {
            map = table.get(userId);
        }

        String newID = "";
        do {
            newID = IDGen.nextID();
        } while (map.containsKey(newID));

        LongRunningRequest newLRR = new LongRunningRequest();
        newLRR.setId(newID);
        newLRR.setTaskType(LRRType.VIRTUALMACHINEREQUEST);
        newLRR.setTaskStatus(LRRStatus.SCHEDULED);
        newLRR.setLrRData(vmRequest);

        map.put(newLRR.getId(), newLRR);
        return newLRR;
    }

    public List<LongRunningRequest> getAllLRR(String userId) {
        HashMap<String, LongRunningRequest> map = table.get(userId);
        if (map == null) {
            return Collections.emptyList();
        } else {
            return ImmutableList.copyOf(map.values());
        }

    }

    private final class IDGenerator {
        private SecureRandom random = new SecureRandom();

        public String nextID() {
            return new BigInteger(130, random).toString(32);
        }
    }
}
