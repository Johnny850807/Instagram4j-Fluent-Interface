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

import tw.waterball.api.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Wrapper wraps around Instagram4J's payload object by Instagram-fluent-API's interface.
 * @author johnny850807 (Waterball)
 */
public class AdapterWrapping {
    public static List<InstagramUser> wrap4JUser
            (Instagram4JAdapter ig, List<org.brunocvcunha.instagram4j.requests.payload.InstagramUser> users) {
        return users.stream()
                .map(us -> new Instagram4JUserAdapter(ig, us.getUsername()))
                .collect(Collectors.toList());
    }

    public static List<InstagramUser> wrap4JUserSummaries
            (Instagram4JAdapter ig, List<org.brunocvcunha.instagram4j.requests.payload.InstagramUserSummary> userSummaries) {
        return userSummaries.stream()
                .map(us -> new Instagram4JUserAdapter(ig, us.getUsername()))
                .collect(Collectors.toList());
    }

    public static List<InstagramFeed> wrap4JFeedItems
            (Instagram4JAdapter ig, List<org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem> items) {
        return items.stream()
                .map(item -> new Instagram4JFeedAdapter(ig, item))
                .collect(Collectors.toList());
    }

    public static List<InstagramComment> wrap4JComments
            (Instagram4JAdapter ig, List<org.brunocvcunha.instagram4j.requests.payload.InstagramComment> comments) {
        return comments.stream()
                .map(comment -> new Instagram4JCommentAdapter(ig, comment))
                .collect(Collectors.toList());
    }

    public static List<InstagramInboxThread> wrap4JInboxThreads(Instagram4JAdapter ig,
                                                         List<org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThread> threads) {
        return threads.stream()
                .map(thread -> new Instagram4JInboxThreadAdapter(ig, thread))
                .collect(Collectors.toList());
    }

    public static List<InstagramPendingInboxThread> wrap4JPendingInboxThreads(Instagram4JAdapter ig,
                                                                       List<org.brunocvcunha.instagram4j.requests.payload.InstagramInboxThread> threads) {
        return threads.stream()
                .map(thread -> new Instagram4JPendingInboxThreadAdapter(ig, thread))
                .collect(Collectors.toList());
    }
}
