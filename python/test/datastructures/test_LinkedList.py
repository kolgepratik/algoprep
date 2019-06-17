import unittest

from python.main.datastructures.LinkedList import LinkedList

if __name__ == '__main__':
    unittest.main()


class LinkedListTest(unittest.TestCase):
    def setUp(self) -> None:
        self.linked_list = LinkedList()

        self.linked_list.append(1)
        self.linked_list.append(2)
        self.linked_list.append(3)
        self.linked_list.append(4)

        print(self.linked_list)

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

    def test_is_cyclic(self):
        print("test_is_cyclic")

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
