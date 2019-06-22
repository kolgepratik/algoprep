from typing import Optional


class LinkedListNode:
    def __init__(self, data):
        self.data = data
        self.next = None

    def __str__(self) -> str:
        return "Node: {}".format(self.data)


class LinkedList:
    def __init__(self):
        self.head = None

    def __str__(self) -> str:
        if self.head is not None:
            str_list = list()
            str_list.append("> LinkedList size: {}".format(self.size()))

            current = self.head
            while current is not None:
                str_list.append(current.__str__())
                current = current.next

            return "\n".join(str_list)

        return ""

    def size(self) -> int:
        sz = 0

        current = self.head
        while current is not None:
            current = current.next
            sz += 1

        return sz

    def size_recursive(self) -> int:
        return self.size_recursive_helper(self.head)

    def size_recursive_helper(self, current_node) -> int:
        if current_node is None:
            return 0

        return 1 + self.size_recursive_helper(current_node.next)

    def is_size_even(self) -> bool:
        if self.head is not None:
            slow_node_ptr = self.head
            fast_node_ptr = self.head

            while fast_node_ptr is not None:
                if fast_node_ptr.next is None:
                    return False
                
                slow_node_ptr = slow_node_ptr.next
                fast_node_ptr = fast_node_ptr.next.next

            return True

        return False

    # Add
    def append(self, data):
        node = LinkedListNode(data)

        if self.head is None:
            self.head = node
            return

        current = self.head
        while current.next is not None:
            current = current.next

        current.next = node

    def prepend(self, data):
        node = LinkedListNode(data)

        node.next = self.head
        self.head = node

    def add_at_index(self, index, data):
        if self is not None:
            if index == 1:
                self.prepend(data)
                return

            node = LinkedListNode(data)

            current = self.head.next
            current_index = 2
            prev = self.head

            while current is not None and current_index < index:
                temp = current.next
                prev = current
                current = temp
                current_index += 1

            if current_index == index:
                prev.next = node
                node.next = current

    # Reverse
    def reverse(self):
        if self.head is not None:
            prev = self.head
            current = prev.next
            next = current.next

            prev.next = None
            while current is not None:
                current.next = prev

                prev = current
                current = next

                if next is not None:
                    next = next.next

            self.head = prev

    def reverse_recursive(self):
        if self.head is not None:
            old_head = self.head
            self.head = self.reverse_recursive_helper(self.head)
            old_head.next = None

    def reverse_recursive_helper(self, current_head):
        if current_head.next is None:
            return current_head

        new_head = self.reverse_recursive_helper(current_head.next)

        current_head.next.next = current_head

        return new_head

    # Access
    def get_last_node(self):
        if self.head is None:
            return None

        current = self.head
        while current.next is not None:
            current = current.next

        return current

    def get_last_node_recursive(self):
        if self.head is None:
            return None

        last = self.get_last_helper_recursive(self.head)

        return last

    def get_last_helper_recursive(self, current_head):
        if current_head.next is None:
            return current_head

        return self.get_last_helper_recursive(current_head.next)

    def get_first_node(self):
        return self.head

    def get_middle_node(self):
        if self.head is not None:
            slow_node_ptr = self.head
            fast_node_ptr = self.head

            while fast_node_ptr is not None and fast_node_ptr.next is not None:
                slow_node_ptr = slow_node_ptr.next
                fast_node_ptr = fast_node_ptr.next.next

            return slow_node_ptr

        return None


    # Delete
    def clear(self):
        self.head = None

    def delete_node_with_data(self, data):
        if self.head is not None:
            current = self.head
            prev = current

            while current is not None and current.data != data:
                prev = current
                current = current.next

            if current is not None:
                prev.next = current.next

    # Find
    def find_node_with_data(self, data: int) -> Optional[LinkedListNode]:
        if self.head is not None:
            current_node = self.head

            while current_node is not None:
                if current_node.data == data:
                    return current_node

                current_node = current_node.next

        return None

    def find_node_with_data_recursive(self, data: int) -> Optional[LinkedListNode]:
        if self.head is not None:
            return self.find_node_with_data_recursive_helper(self.head, data)

        return None

    def find_node_with_data_recursive_helper(self, current_node, data: int) -> Optional[LinkedListNode]:
        if current_node is not None:
            if current_node.data == data:
                return current_node
            else:
                return self.find_node_with_data_recursive_helper(current_node.next, data)

        return None

    def find_node_at_index(self, index: int) -> Optional[LinkedListNode]:
        if self.head is not None:
            current_index = 1
            current_node = self.head

            while current_node is not None and current_index != index:
                current_index += 1
                current_node = current_node.next

            return current_node

        return None

    def find_nth_node_from_end(self, n) -> Optional[LinkedListNode]:
        if self.head is not None:
            current_node = self.head
            remaining_size = self.size()

            while current_node is not None and remaining_size != n:
                current_node = current_node.next
                remaining_size -= 1

            return current_node

        return None

    def find_nth_node_from_end_optimized(self, n) -> Optional[LinkedListNode]:
        if self.head is not None:
            nth_node_from_end_counter = 0
            nth_node_from_end = None
            current_node = self.head

            while current_node is not None:
                current_node = current_node.next
                nth_node_from_end_counter += 1

                if nth_node_from_end_counter == n:
                    nth_node_from_end = self.head

                if nth_node_from_end_counter > n:
                    nth_node_from_end = nth_node_from_end.next

            return nth_node_from_end

        return None

    # Cyclic
    def find_cycle_start_node(self) -> Optional[LinkedListNode]:
        if self.head is not None:
            ptr_slow = self.head
            ptr_fast = self.head

            while ptr_slow is not None and ptr_fast is not None and ptr_fast.next is not None:
                ptr_slow = ptr_slow.next
                ptr_fast = ptr_fast.next.next

                if ptr_slow is ptr_fast:
                    # Found cycle. Now find its start.
                    ptr_head = self.head

                    while ptr_head is not ptr_slow:
                        ptr_head = ptr_head.next
                        ptr_slow = ptr_slow.next

                    return ptr_slow

        return None

    # Swap nodes
    def swap_nodes(self, first_node_data, other_node_data): 
        if self.head is not None:
            current_node = self.head.next
            first_node_prev = None
            other_node_prev = None

            while current_node.next is not None:
                if current_node.next.data == first_node_data:
                    first_node_prev = current_node
                
                if current_node.next.data == other_node_data:
                    other_node_prev = current_node

                current_node = current_node.next

            if self.head.data == first_node_data:
                if other_node_prev is not None:
                    temp_node = self.head
                    self.head = other_node_prev.next
                    other_node_prev.next = self.head

            if first_node_prev is not None and other_node_prev is not None:
                temp_node = first_node_prev
                first_node_prev.next = other_node_prev.next
                other_node_prev.next = temp_node.next


                
