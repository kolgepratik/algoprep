import unittest

if __name__ == '__main__':
    unittest.main()


class LinkedListTest(unittest.TestCase):
    def setUp(self) -> None:
        from src.main.datastructures.LinkedList import LinkedList

        self.linked_list = LinkedList()

        self.linked_list.append(1)
        self.linked_list.append(2)
        self.linked_list.append(3)
        self.linked_list.append(4)

        self.assertEqual(4, self.linked_list.size())

    def test_size(self):
        # Act
        size = self.linked_list.size()

        self.assertEqual(4, size)

    def test_size_recursive(self):
        # Act
        size = self.linked_list.size_recursive()

        self.assertEqual(4, size)

    def test_append(self):
        self.linked_list.clear()

        # Act
        self.linked_list.append(1)

        self.assertIsNotNone(self.linked_list.get_first_node())
        self.assertEqual(1, self.linked_list.size())
        self.assertEqual(1, self.linked_list.get_first_node().data)

    def test_prepend(self):
        self.linked_list.clear()

        self.linked_list.append(1)
        self.assertEqual(1, self.linked_list.get_first_node().data)

        # Act
        self.linked_list.prepend(2)

        self.assertEqual(2, self.linked_list.get_first_node().data)

    def test_get_first_node(self):
        # Act
        first_node = self.linked_list.get_first_node()

        self.assertIsNotNone(first_node)
        self.assertEqual(1, first_node.data)

    def test_get_last_node(self):
        # Act
        last_node = self.linked_list.get_last_node()

        self.assertIsNotNone(last_node)
        self.assertEqual(4, last_node.data)

    def test_get_last_node_recursive(self):
        # Act
        last_node = self.linked_list.get_last_node_recursive()

        self.assertIsNotNone(last_node)
        self.assertEqual(4, last_node.data)

    def test_reverse(self):
        self.assertEqual(4, self.linked_list.size())
        self.assertEqual(1, self.linked_list.get_first_node().data)
        self.assertEqual(4, self.linked_list.get_last_node().data)

        # Act
        self.linked_list.reverse()

        self.assertEqual(4, self.linked_list.size())
        self.assertEqual(4, self.linked_list.get_first_node().data)
        self.assertEqual(1, self.linked_list.get_last_node().data)

    def test_reverse_recursive(self):
        self.assertEqual(4, self.linked_list.size())
        self.assertEqual(1, self.linked_list.get_first_node().data)
        self.assertEqual(4, self.linked_list.get_last_node().data)

        # Act
        self.linked_list.reverse_recursive()

        self.assertEqual(4, self.linked_list.size())
        self.assertEqual(4, self.linked_list.get_first_node().data)
        self.assertEqual(1, self.linked_list.get_last_node().data)

    def test_find_node_with_data(self):
        # Act
        found = self.linked_list.find_node_with_data(2)
        not_found = self.linked_list.find_node_with_data(999)

        self.assertIsNotNone(found)
        self.assertEqual(2, found.data)

        self.assertIsNone(not_found)

    def test_find_node_with_data_recursive(self):
        found = self.linked_list.find_node_with_data_recursive(2)
        not_found = self.linked_list.find_node_with_data_recursive(999)

        self.assertIsNotNone(found)
        self.assertEqual(2, found.data)

        self.assertIsNone(not_found)

    def test_find_node_at_index(self):
        # Act
        at_2 = self.linked_list.find_node_at_index(2)
        at_999 = self.linked_list.find_node_at_index(999)

        self.assertIsNotNone(at_2)
        self.assertEqual(2, at_2.data)

        self.assertIsNone(at_999)

    def test_find_nth_node_from_end(self):
        # Act
        first_from_last = self.linked_list.find_nth_node_from_end(1)
        third_from_last = self.linked_list.find_nth_node_from_end(3)
        tenth_from_last = self.linked_list.find_nth_node_from_end(10)

        self.assertIsNotNone(first_from_last)
        self.assertEqual(4, first_from_last.data)

        self.assertIsNotNone(third_from_last)
        self.assertEqual(2, third_from_last.data)

        self.assertIsNone(tenth_from_last)

    def test_find_nth_node_from_end_optimized(self):
        # Act
        first_from_last = self.linked_list.find_nth_node_from_end_optimized(1)
        third_from_last = self.linked_list.find_nth_node_from_end_optimized(3)
        tenth_from_last = self.linked_list.find_nth_node_from_end_optimized(10)

        self.assertIsNotNone(first_from_last)
        self.assertEqual(4, first_from_last.data)

        self.assertIsNotNone(third_from_last)
        self.assertEqual(2, third_from_last.data)

        self.assertIsNone(tenth_from_last)

    def test_find_middle_node_odd(self):
        self.linked_list.append(5)
        self.assertEqual(5, self.linked_list.size())

        # Act
        middle_node = self.linked_list.get_middle_node()

        self.assertIsNotNone(middle_node)
        self.assertEqual(3, middle_node.data)

    def test_find_middle_node_even(self):
        # Act
        middle_node = self.linked_list.get_middle_node()

        self.assertIsNotNone(middle_node)
        self.assertEqual(3, middle_node.data)

    def test_is_list_size_even_true(self):
        # Act
        is_even = self.linked_list.is_size_even()

        self.assertEqual(True, is_even)

    def test_is_list_size_even_false(self):
        self.linked_list.append(5)
        self.assertEqual(5, self.linked_list.size())

        # Act
        is_even = self.linked_list.is_size_even()

        self.assertEqual(False, is_even)

    def test_is_cyclic(self):
        self.assertEqual(4, self.linked_list.size())

        found_2 = self.linked_list.find_node_with_data(2)
        self.assertIsNotNone(found_2)
        self.assertEqual(3, found_2.next.data)

        found_3 = self.linked_list.find_node_with_data(3)
        self.assertIsNotNone(found_3)
        self.assertEqual(4, found_3.next.data)

        found_3.next = found_2

        # Act
        cycle_start_node = self.linked_list.find_cycle_start_node()

        self.assertIsNotNone(cycle_start_node)
        self.assertEqual(2, cycle_start_node.data)

    def test_clear(self):
        self.assertIsNotNone(self.linked_list.head)

        # Act
        self.linked_list.clear()

        self.assertIsNone(self.linked_list.head)

    def test_delete_node_with_data(self):
        self.assertEqual(4, self.linked_list.size())
        self.assertIsNotNone(self.linked_list.find_node_with_data(3))

        # Act
        self.linked_list.delete_node_with_data(3)

        self.assertEqual(3, self.linked_list.size())
        self.assertIsNone(self.linked_list.find_node_with_data(3))

    def test_swap_nodes(self):
        # Act
        self.linked_list.swap_nodes(2, 3)

        print(self.linked_list)
