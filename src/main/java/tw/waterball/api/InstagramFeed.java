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

import java.util.List;
import java.util.function.Predicate;

public interface InstagramFeed extends InstagramPk {
    String getContent();
    InstagramFeed like();
    InstagramFeed unlike();
    InstagramFeed comment(String message);
    List<InstagramComment> getRecentComments();
    List<InstagramComment> getComments(int maxNum);

    default List<InstagramComment> getAllComments(int maxNum) {
        return getComments(Integer.MAX_VALUE);
    }

    List<InstagramUser> getRecentCommenters();
    List<InstagramUser> getCommenters(int maxNum);
    List<InstagramUser> getDistinctCommenters(int maxNum);

    default List<InstagramUser> getAllCommenters() {
        return getCommenters(Integer.MAX_VALUE);
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
        return new ForeachUser(getCommenters(maxNum));
    }

    default ForeachUser foreachDistinctCommenters(int maxNum) {
        return new ForeachUser(getDistinctCommenters(maxNum));
    }

    default ForeachUser foreachAllCommenters() {
        return new ForeachUser(getAllCommenters());
    }

}
