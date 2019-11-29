/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 * Copyright 2019 Johnny850807
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package tw.waterball.impls.instagram4j;

import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import tw.waterball.api.pagination.Pagination;
import tw.waterball.impls.instagram4j.pagination.InstagramFeedPagingIterator;
import tw.waterball.impls.instagram4j.pagination.InstagramFollowerPagingIterator;
import tw.waterball.impls.instagram4j.pagination.InstagramFollowingPagingIterator;

import java.util.Collections;
import java.util.List;

public class Instagram4JUserAdapter extends AbstractInstagram4jUserAdapter {

    public Instagram4JUserAdapter(Instagram4JAdapter ig, org.brunocvcunha.instagram4j.requests.payload.InstagramUser user) {
        super(ig, user);
    }

    public Instagram4JUserAdapter(Instagram4JAdapter ig, String username) {
        super(ig, username);
    }

    @Override
    public boolean hasFollowMe() {
        return getPagedAllFollowings().aggregate().stream()
                .anyMatch(user -> user.getUsername().equals(ig.getUsername()));
    }

    @Override
    public InstagramUser follow() {
        ig.sendRequest(new InstagramFollowRequest(getPK()));
        return this;
    }

    @Override
    public InstagramUser unfollow() {
        ig.sendRequest(new InstagramUnfollowRequest(getPK()));
        return this;
    }

    @Override
    public InstagramUser likeRecentFeeds() {
        getRecentFeeds().forEach(InstagramFeed::like);
        return this;
    }

    @Override
    public List<InstagramUser> getRecentFollowers() {
        return AdapterWrapping.wrap4JUserSummaries(ig, ig.sendRequest(new InstagramGetUserFollowersRequest(getPK())).getUsers());
    }

    @Override
    public List<InstagramUser> getRecentFollowings() {
        return AdapterWrapping.wrap4JUserSummaries(ig, ig.sendRequest(new InstagramGetUserFollowingRequest(getPK())).getUsers());
    }

    @Override
    public Pagination<InstagramUser> getPagedFollowers(int maxNum) {
        return new Pagination<>(new InstagramFollowerPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public Pagination<InstagramUser> getPagedFollowings(int maxNum) {
        return new Pagination<>(new InstagramFollowingPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public List<InstagramFeed> getRecentFeeds() {
        List<InstagramFeedItem> feeds = ig.sendRequest(new InstagramUserFeedRequest(getPK())).getItems();
        return AdapterWrapping.wrap4JFeedItems(ig, feeds);
    }

    @Override
    public Pagination<InstagramFeed> getPagedFeeds(int maxNum) {
        return new Pagination<>(new InstagramFeedPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public InstagramUser sendDM(String message) {
        ig.sendRequest(InstagramDirectShareRequest.builder()
                .shareType(InstagramDirectShareRequest.ShareType.MESSAGE)
                .recipients(Collections.singletonList(String.valueOf(getPK())))
                .message(message).build());
        return this;
    }

}
