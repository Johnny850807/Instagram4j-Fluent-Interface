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

package tw.waterball.api;

import tw.waterball.api.foreach.ForeachUser;
import tw.waterball.api.mocks.MockInstagram4JFeedAdapter;
import tw.waterball.api.pagination.Pagination;

import java.util.List;
import java.util.function.Predicate;

public interface InstagramFeed extends InstagramPk {
    String getContent();
    long getTimeStamp();
    InstagramFeed like();
    InstagramFeed unlike();
    InstagramFeed comment(String message);
    List<InstagramComment> getRecentComments();

    default boolean hasComments() {
        List<InstagramComment> recentComments = getRecentComments();
        return recentComments != null && !recentComments.isEmpty();
    }

    Pagination<InstagramComment> getPagedComments(int maxNum);

    default Pagination<InstagramComment> getPagedAllComments() {
        return getPagedComments(Integer.MAX_VALUE);
    }

    List<InstagramUser> getRecentCommenters();
    Pagination<InstagramUser> getPagedCommenters(int maxNum);
    Pagination<InstagramUser> getPagedDistinctCommenters(int maxNum);

    default Pagination<InstagramUser> getPagedAllCommenters() {
        return getPagedCommenters(Integer.MAX_VALUE);
    }

    default InstagramFeed when(Predicate<InstagramFeed> predicate) {
        if (predicate.test(this))
            return this;
        return new MockInstagram4JFeedAdapter(getPK());
    }

    default ForeachUser foreachRecentCommenters() {
        return new ForeachUser(getRecentCommenters());
    }

    default ForeachUser foreachCommenters(int maxNum) {
        return new ForeachUser(getPagedCommenters(maxNum).aggregate());
    }

    default ForeachUser foreachDistinctCommenters(int maxNum) {
        return new ForeachUser(getPagedDistinctCommenters(maxNum).aggregate());
    }

    default ForeachUser foreachAllCommenters() {
        return new ForeachUser(getPagedAllCommenters().aggregate());
    }

}
