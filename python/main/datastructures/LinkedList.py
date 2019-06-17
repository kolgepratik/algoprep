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

    # Add
    def append(self, data):
        print("append: {}".format(data))
        node = LinkedListNode(data)

        if self.head is None:
            self.head = node
            return

        current = self.head
        while current.next is not None:
            current = current.next

        current.next = node

    def prepend(self, data):
        print("prepend: {}".format(data))
        node = LinkedListNode(data)

        node.next = self.head
        self.head = node

    def add_at(self, index, data):
        print("add_at {}: {}".format(index, data))
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
        print("reverse:")

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
        print("reverse_recursive:")

        if self.head is not None:
            old_head = self.head
            self.head = self.reverse_recursive_helper(self.head)
            old_head.next = None

    def reverse_recursive_helper(self, current_head):
        print("reverse_recursive_helper: {}".format(current_head.data))

        if current_head.next is None:
            return current_head

        new_head = self.reverse_recursive_helper(current_head.next)

        current_head.next.next = current_head

        return new_head

    # Access
    def get_last_node(self):
        print("get_last_node:")

        if self.head is None:
            return None

        current = self.head
        while current.next is not None:
            current = current.next

        print("Last Node: {}".format(current.data))
        return current

    def get_last_node_recursive(self):
        print("get_last_node_recursive:")

        if self.head is None:
            return None

        last = self.get_last_helper_recursive(self.head)
        print("Last Node: {}".format(last.data))

        return last

    def get_last_helper_recursive(self, current_head):
        print("get_last_helper_recursive: {}".format(current_head.data))

        if current_head.next is None:
            return current_head

        return self.get_last_helper_recursive(current_head.next)

    def get_first_node(self):
        print("get_first_node:")
        return self.head

    # Clear
    def clear(self):
        print("clear:")
        self.head = None

    # Find
    def find_node_with_data(self, data: int) -> Optional[LinkedListNode]:
        print("find_node_with_data: {}".format(data))

        if self.head is not None:
            current_node = self.head

            while current_node is not None:
                if current_node.data == data:
                    return current_node

                current_node = current_node.next

        return None

    # Cyclic
    def find_cycle_start_node(self) -> Optional[LinkedListNode]:
        print("find_cycle_start_node:")

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
