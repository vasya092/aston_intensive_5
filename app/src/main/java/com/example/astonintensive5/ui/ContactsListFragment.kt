package com.example.astonintensive5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.astonintensive5.R
import com.example.astonintensive5.databinding.FragmentContactsListBinding
import com.example.astonintensive5.model.Contact

class ContactsListFragment : Fragment() {

    private val contactsViewModel: ContactsViewModel by activityViewModels()
    private lateinit var slidingPaneLayout: SlidingPaneLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentContactsListBinding.bind(view)
        slidingPaneLayout = binding.slidingPaneLayout
        setupPaneLayout()
        val adapter = ContactsListAdapter { openContactDetailFragment(it) }
        contactsViewModel.contactsData.observe(this.viewLifecycleOwner) {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        }

        binding.contactsList.adapter = adapter

        contactsViewModel.isContactSelected.observe(this.viewLifecycleOwner) { isContactSelected ->
            if (!isContactSelected) {
                slidingPaneLayout.closePane()
            }
        }
    }

    private fun selectContact(contact: Contact) {
        contactsViewModel.onCurrentContactChange(contact)
        contactsViewModel.onSelectedContactStateChange(true)
    }

    private fun openContactDetailFragment(contact: Contact) {
        selectContact(contact)
        childFragmentManager.commit {
            setReorderingAllowed(true)
            replace<ContactDetailFragment>(R.id.contact_detail_container)
            if (slidingPaneLayout.isOpen) {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            }
        }
        slidingPaneLayout.open()
    }

    private fun setupPaneLayout() {
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_UNLOCKED
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            ContactsListOnBackPressure(slidingPaneLayout)
        )
    }

    override fun onResume() {
        super.onResume()
        slidingPaneLayout.closePane()
    }
}