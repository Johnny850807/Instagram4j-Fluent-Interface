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

package tw.waterball.api.mocks;

import tw.waterball.api.InstagramComment;
import tw.waterball.api.InstagramFeed;
import tw.waterball.api.InstagramUser;
import tw.waterball.impls.instagram4j.AbstractInstagram4JFeedAdapter;
import tw.waterball.impls.instagram4j.Instagram4JAdapter;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;

import java.util.Collections;
import java.util.List;

public class MockInstagram4JFeedAdapter extends AbstractInstagram4JFeedAdapter {
    public MockInstagram4JFeedAdapter(Instagram4JAdapter ig, InstagramFeedItem feed) {
        super(feed);
    }

    public MockInstagram4JFeedAdapter(long pk) {
        super(pk);
    }

    @Override
    public InstagramFeed like() {
        return this;
    }

    @Override
    public InstagramFeed unlike() {
        return this;
    }

    @Override
    public InstagramFeed comment(String message) {
        return this;
    }

    @Override
    public List<InstagramComment> getRecentComments() {
        return Collections.emptyList();
    }

    @Override
    public List<InstagramComment> getComments(int maxNum) {
        return Collections.emptyList();
    }

    @Override
    public List<InstagramUser> getRecentCommenters() {
        return Collections.emptyList();
    }

    @Override
    public List<InstagramUser> getCommenters(int maxNum) {
        return Collections.emptyList();
    }

    @Override
    public List<InstagramUser> getDistinctCommenters(int maxNum) {
        return Collections.emptyList();
    }
}