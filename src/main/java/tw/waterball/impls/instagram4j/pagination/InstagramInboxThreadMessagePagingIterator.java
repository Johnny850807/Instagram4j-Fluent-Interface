/*
 * Copyright 2019 johnny850807
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tw.waterball.impls.instagram4j.pagination;

import org.brunocvcunha.instagram4j.requests.InstagramGetInboxThreadRequest;
import org.brunocvcunha.instagram4j.requests.InstagramGetUserFollowingRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetUserFollowersResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThreadResult;
import tw.waterball.api.InstagramInboxThreadMessage;
import tw.waterball.api.InstagramUser;
import tw.waterball.api.pagination.Page;
import tw.waterball.api.pagination.PagingIterator;
import tw.waterball.impls.instagram4j.AdapterWrapping;
import tw.waterball.impls.instagram4j.Instagram4JAdapter;

import java.util.Map;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class InstagramInboxThreadMessagePagingIterator extends PagingIterator<InstagramInboxThreadMessage> {
    private Instagram4JAdapter ig;
    private String threadId;
    private String nextMaxId;
    private InstagramInboxThreadResult result;
    private Map<String, String> userIdToUserNameMap;

    public InstagramInboxThreadMessagePagingIterator(Instagram4JAdapter ig, String threadId) {
        this.ig = ig;
        this.threadId = threadId;
    }

    public InstagramInboxThreadMessagePagingIterator(int limit, Instagram4JAdapter ig, String threadId) {
        super(limit);
        this.ig = ig;
        this.threadId = threadId;
    }

    @Override
    protected boolean hasNextPage() {
        return result == null /*first fetch*/ || result.getThread().has_older;
    }

    @Override
    protected Page<InstagramInboxThreadMessage> getNextPage() {
        this.result = ig.sendRequest(new InstagramGetInboxThreadRequest(threadId, nextMaxId));
        nextMaxId = result.getThread().getOldest_cursor();
        return new Page<>(AdapterWrapping.wrap4JInboxThreadMessage(result.getThread().getItems()));
    }
}
