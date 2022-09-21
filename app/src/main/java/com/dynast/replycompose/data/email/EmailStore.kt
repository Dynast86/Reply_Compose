/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dynast.replycompose.data.email

import com.dynast.replycompose.R
import com.dynast.replycompose.data.compose.Account
import com.dynast.replycompose.data.compose.allUserAccounts
import com.dynast.replycompose.data.compose.allUserContactAccounts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A static data store of [Email]s.
 */
object EmailStore {

    private val allEmails = mutableListOf(
        Email(
            0L,
            getContactAccountById(9L),
            listOf(getDefaultUserAccount()),
            "Package shipped!",
            """
                Cucumber Mask Facial has shipped.

                Keep an eye out for a package to arrive between this Thursday and next Tuesday. If for any reason you don't receive your package before the end of next week, please reach out to us for details on your shipment.

                As always, thank you for shopping with us and we hope you love our specially formulated Cucumber Mask!
            """.trimIndent(),
            isStarred = true
        ),
        Email(
            1L,
            getContactAccountById(6L),
            listOf(getDefaultUserAccount()),
            "Brunch this weekend?",
            """
                I'll be in your neighborhood doing errands and was hoping to catch you for a coffee this Saturday. If you don't have anything scheduled, it would be great to see you! It feels like its been forever.

                If we do get a chance to get together, remind me to tell you about Kim. She stopped over at the house to say hey to the kids and told me all about her trip to Mexico.

                Talk to you soon,

                Ali
            """.trimIndent()
        ),
        Email(
            2L,
            getContactAccountById(5L),
            listOf(getDefaultUserAccount()),
            "Bonjour from Paris",
            "Here are some great shots from my trip...",
            listOf(
                EmailAttachment(R.drawable.paris_1, "Bridge in Paris"),
                EmailAttachment(R.drawable.paris_2, "Bridge in Paris at night"),
                EmailAttachment(R.drawable.paris_3, "City street in Paris"),
                EmailAttachment(R.drawable.paris_4, "Street with bike in Paris")
            ),
            true
        ),
        Email(
            3L,
            getContactAccountById(8L),
            listOf(getDefaultUserAccount()),
            "High school reunion?",
            """
                Hi friends,

                I was at the grocery store on Sunday night.. when I ran into Genie Williams! I almost didn't recognize her afer 20 years!

                Anyway, it turns out she is on the organizing committee for the high school reunion this fall. I don't know if you were planning on going or not, but she could definitely use our help in trying to track down lots of missing alums. If you can make it, we're doing a little phone-tree party at her place next Saturday, hoping that if we can find one person, thee more will...
            """.trimIndent(),
            mailbox = Mailbox.SENT
        ),
        Email(
            4L,
            getContactAccountById(11L),
            listOf(
                getDefaultUserAccount(),
                getContactAccountById(8L),
                getContactAccountById(5L)
            ),
            "Brazil trip",
            """
                Thought we might be able to go over some details about our upcoming vacation.

                I've been doing a bit of research and have come across a few paces in Northern Brazil that I think we should check out. One, the north has some of the most predictable wind on the planet. I'd love to get out on the ocean and kitesurf for a couple of days if we're going to be anywhere near or around Taiba. I hear it's beautiful there and if you're up for it, I'd love to go. Other than that, I haven't spent too much time looking into places along our road trip route. I'm assuming we can find places to stay and things to do as we drive and find places we think look interesting. But... I know you're more of a planner, so if you have ideas or places in mind, lets jot some ideas down!

                Maybe we can jump on the phone later today if you have a second.
            """.trimIndent(),
            isStarred = true
        ),
        Email(
            5L,
            getContactAccountById(13L),
            listOf(getDefaultUserAccount()),
            "Update to Your Itinerary",
            ""
        ),
        Email(
            6L,
            getContactAccountById(10L),
            listOf(getDefaultUserAccount()),
            "Recipe to try",
            "Raspberry Pie: We should make this pie recipe tonight! The filling is " +
                    "very quick to put together.",
            mailbox = Mailbox.SENT
        ),
        Email(
            7L,
            getContactAccountById(9L),
            listOf(getDefaultUserAccount()),
            "Delivered",
            "Your shoes should be waiting for you at home!"
        ),
        Email(
            8L,
            getContactAccountById(13L),
            listOf(getDefaultUserAccount()),
            "Your update on Google Play Store is live!",
            """
              Your update, 0.1.1, is now live on the Play Store and available for your alpha users to start testing.
              
              Your alpha testers will be automatically notified. If you'd rather send them a link directly, go to your Google Play Console and follow the instructions for obtaining an open alpha testing link.
          """.trimIndent(),
            mailbox = Mailbox.TRASH
        ),
        Email(
            9L,
            getContactAccountById(10L),
            listOf(getDefaultUserAccount()),
            "(No subject)",
            """
            Hey, 
            
            Wanted to email and see what you thought of
          """.trimIndent(),
            mailbox = Mailbox.DRAFTS
        ),
        Email(
            10L,
            getContactAccountById(5L),
            listOf(getDefaultUserAccount()),
            "Try a free TrailGo account",
            """
            Looking for the best hiking trails in your area? TrailGo gets you on the path to the outdoors faster than you can pack a sandwich. 
            
            Whether you're an experienced hiker or just looking to get outside for the afternoon, there's a segment that suits you.
          """.trimIndent(),
            mailbox = Mailbox.TRASH
        ),
        Email(
            10L,
            getContactAccountById(5L),
            listOf(getDefaultUserAccount()),
            "Free money",
            """
            You've been selected as a winner in our latest raffle! To claim your prize, click on the link.
          """.trimIndent(),
            mailbox = Mailbox.SPAM
        )
    )

    private val _emails: MutableStateFlow<List<Email>> = MutableStateFlow(
        emptyList()
    )

    private val index: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.mailbox == Mailbox.INBOX } }

    private val starred: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.isStarred } }


    private val sent: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.mailbox == Mailbox.SENT } }

    private val trash: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.mailbox == Mailbox.TRASH } }

    private val spam: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.mailbox == Mailbox.SPAM } }

    private val drafts: StateFlow<List<Email>>
        get() = _emails.apply { value.filter { it.mailbox == Mailbox.DRAFTS } }

    init {
        _emails.value = allEmails
    }

    fun getEmails(mailbox: Mailbox): StateFlow<List<Email>> {
        return when (mailbox) {
            Mailbox.INBOX -> index
            Mailbox.STARRED -> starred
            Mailbox.SENT -> sent
            Mailbox.TRASH -> trash
            Mailbox.SPAM -> spam
            Mailbox.DRAFTS -> drafts
        }
    }

    /**
     * Get an [Email] with the given [id].
     */
    fun get(id: Long): Email? {
        return allEmails.firstOrNull { it.id == id }
    }

    /**
     * Create a new, blank [Email].
     */
    fun create(): Email {
        return Email(
            System.nanoTime(), // Unique ID generation.
            getDefaultUserAccount()
        )
    }

    /**
     * Create a new [Email] that is a reply to the email with the given [replyToId].
     */
    fun createReplyTo(replyToId: Long): Email {
        val replyTo = get(replyToId) ?: return create()
        return Email(
            id = System.nanoTime(),
            sender = replyTo.recipients.firstOrNull() ?: getDefaultUserAccount(),
            recipients = listOf(replyTo.sender) + replyTo.recipients,
            subject = replyTo.subject,
            isStarred = replyTo.isStarred,
            isImportant = replyTo.isImportant
        )
    }

    /**
     * Delete the [Email] with the given [id].
     */
    fun delete(id: Long) {
        update(id) { mailbox = Mailbox.TRASH }
    }

    /**
     * Update the [Email] with the given [id] by applying all mutations from [with].
     */
    fun update(id: Long, with: Email.() -> Unit) {
        allEmails.find { it.id == id }?.let {
            it.with()
            _emails.value = allEmails
        }
    }

    /**
     * Get a list of [EmailFolder]s by which [Email]s can be categorized.
     */
    fun getAllFolders() = listOf(
        "Receipts",
        "Pine Elementary",
        "Taxes",
        "Vacation",
        "Mortgage",
        "Grocery coupons"
    )

    fun getDefaultUserAccount() = allUserAccounts.first()

    fun getContactAccountById(accountId: Long): Account {
        return allUserContactAccounts.firstOrNull { it.id == accountId } ?: allUserContactAccounts.first()
    }

    fun isUserAccount(uid: Long): Boolean = allUserAccounts.any { it.uid == uid }
}

