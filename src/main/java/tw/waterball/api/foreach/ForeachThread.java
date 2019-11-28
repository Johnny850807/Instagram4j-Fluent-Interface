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
package tw.waterball.api.foreach;

import tw.waterball.api.InstagramInboxThread;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author johnny850807 (Waterball)
 */
public class ForeachThread {
    @SuppressWarnings("WeakerAccess")
    protected List<? extends InstagramInboxThread> items;

    public ForeachThread(List<? extends InstagramInboxThread> items) {
        this.items = items;
    }

    public ForeachThread foreach(Consumer<? super InstagramInboxThread> consumer) {
        items.forEach(consumer);
        return this;
    }

    public ForeachThread take(int num) {
        return new ForeachThread(items.subList(0, Math.min(items.size(), num)));
    }

    public ForeachThread filter(Predicate<? super InstagramInboxThread> predicate) {
        return new ForeachThread(
                items.stream().filter(predicate).collect(Collectors.toList())
        );
    }

    public ForeachThread reply(String message) {
        items.forEach(thread -> thread.reply(message));
        return this;
    }

    public List<? extends InstagramInboxThread> get() {
        return items;
    }
}
