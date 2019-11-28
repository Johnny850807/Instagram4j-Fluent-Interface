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

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ForeachString {
    private List<String> items;

    public ForeachString(List<String> items) {
        this.items = items;
    }

    public ForeachString take(int num) {
        return new ForeachString(items.subList(0, Math.min(items.size(), num)));
    }

    public ForeachString filter(Predicate<String> predicate) {
        return new ForeachString(
                items.stream().filter(predicate).collect(Collectors.toList())
        );
    }

    public List<String> get() {
        return items;
    }

    public void println() {
        for (String item : items) {
            System.out.println(item);
        }
    }
}
