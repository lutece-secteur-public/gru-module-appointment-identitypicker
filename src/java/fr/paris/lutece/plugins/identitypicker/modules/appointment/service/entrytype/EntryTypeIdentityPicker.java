/*
 * Copyright (c) 2002-2025, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.identitypicker.modules.appointment.service.entrytype;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import fr.paris.lutece.plugins.genericattributes.business.Entry;
import fr.paris.lutece.plugins.genericattributes.business.Field;
import fr.paris.lutece.plugins.genericattributes.modules.identitypicker.service.entrytype.AbstractEntryTypeIdentityPicker;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.ReferenceList;

/**
 *
 * class EntryTypeIdentityPicker
 *
 */
public class EntryTypeIdentityPicker extends AbstractEntryTypeIdentityPicker
{
    private static final String TEMPLATE_CREATE = "admin/plugins/appointment/entries/create_entry_type_identity_picker.html";
    private static final String TEMPLATE_MODIFY = "admin/plugins/appointment/entries/modify_entry_type_identity_picker.html";
    private static final String TEMPLATE_READONLY_BACKOFFICE = "admin/plugins/appointment/entries/readonly_entry_type_identity_picker.html";
    private static final String TEMPLATE_HTML_CODE_ADMIN = "admin/plugins/appointment/entries/html_code_entry_type_identity_picker.html";
    private static final String TEMPLATE_HTML_CODE_ADMIN_CUID_ONLY = "admin/plugins/appointment/entries/html_code_entry_type_identity_picker_only_cuid.html";
    private static final String TEMPLATE_HTML_CODE = "skin/plugins/appointment/entries/html_code_entry_type_identity_picker.html";
    private static final String TEMPLATE_READONLY_FRONTOFFICE = "skin/plugins/appointment/entries/readonly_entry_type_identity_picker.html";
    private static final String FIELD_STORAGE_IDENTITY = "storage_identity";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRequestData( Entry entry, HttpServletRequest request, Locale locale )
    {
        String strResult = super.getRequestData( entry, request, locale );

        if ( strResult == null )
        {
            // Update field titles with display names from identity store
            ReferenceList refList = getIdentityAttributesRefList( );

            for ( ReferenceItem item : refList )
            {
                Field field = entry.getFieldByCode( item.getCode( ) );

                if ( field != null )
                {
                    field.setTitle( item.getName( ) );
                }
            }
        }

        return strResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateHtmlForm( Entry entry, boolean bDisplayFront )
    {
        if ( bDisplayFront )
        {
            return TEMPLATE_HTML_CODE;
        }

        if ( entry.getFieldByCode( FIELD_STORAGE_IDENTITY ).getValue( ).equals( "true" ) )
        {
            return TEMPLATE_HTML_CODE_ADMIN;
        }

        return TEMPLATE_HTML_CODE_ADMIN_CUID_ONLY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateCreate( Entry entry, boolean bDisplayFront )
    {
        return TEMPLATE_CREATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateModify( Entry entry, boolean bDisplayFront )
    {
        return TEMPLATE_MODIFY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTemplateEntryReadOnly( boolean bDisplayFront )
    {
        if ( bDisplayFront )
        {
            return TEMPLATE_READONLY_FRONTOFFICE;
        }

        return TEMPLATE_READONLY_BACKOFFICE;
    }
}
