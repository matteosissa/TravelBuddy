package dadm.ndescot.travelbuddy.ui.favourites

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteAllDialogFragment: DialogFragment() {
    private val viewModel: FavouritesViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Delete all favourite quotations")
            .setMessage("Do you really want to delete all your quotations?")
            .setPositiveButton("Delete") { _, _ ->
                viewModel.deleteAllQuotations()
                dismiss()
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .create()
    }

}