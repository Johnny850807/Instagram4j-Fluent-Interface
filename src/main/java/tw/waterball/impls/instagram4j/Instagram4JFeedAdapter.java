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

import tw.waterball.api.InstagramComment;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import org.brunocvcunha.instagram4j.requests.*;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import tw.waterball.api.pagination.Pagination;
import tw.waterball.impls.instagram4j.pagination.InstagramCommentPagingIterator;
import tw.waterball.impls.instagram4j.pagination.InstagramCommenterPagingIterator;
import tw.waterball.impls.instagram4j.pagination.InstagramDistinctCommenterPagingIterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Instagram4JFeedAdapter extends AbstractInstagram4JFeedAdapter {
    private transient Instagram4JAdapter ig;

    Instagram4JFeedAdapter(Instagram4JAdapter ig, InstagramFeedItem feed) {
        super(feed);
        this.ig = ig;
    }

    @Override
    public InstagramFeed like() {
        ig.sendRequest(new InstagramLikeRequest(getPK()));
        return this;
    }

    @Override
    public InstagramFeed unlike() {
        ig.sendRequest(new InstagramUnlikeRequest(getPK()));
        return this;
    }

    @Override
    public InstagramFeed comment(String message) {
        ig.sendRequest(new InstagramPostCommentRequest(getPK(), message));
        return this;
    }

    @Override
    public List<InstagramComment> getRecentComments() {
        return AdapterWrapping.wrap4JComments(ig, ig.sendRequest(new InstagramGetMediaCommentsRequest(
                String.valueOf(getPK()), null)).getComments());
    }

    @Override
    public Pagination<InstagramComment> getPagedComments(int maxNum) {
        return new Pagination<>(new InstagramCommentPagingIterator(maxNum, ig, getPK()));
    }

    @Override
    public List<InstagramUser> getRecentCommenters() {
        return getRecentComments().stream()
                .map(InstagramComment::getCommenter)
                .collect(Collectors.toList());
    }

    @Override
    public Pagination<InstagramUser> getPagedCommenters(int maxNum) {
        return new Pagination<>(
                new InstagramCommenterPagingIterator(
                        new InstagramCommentPagingIterator(maxNum, ig, getPK())));
    }

    @Override
    public Pagination<InstagramUser> getPagedDistinctCommenters(int maxNum) {
        return new Pagination<>(new InstagramDistinctCommenterPagingIterator(maxNum, ig, getPK()));
    }
}
