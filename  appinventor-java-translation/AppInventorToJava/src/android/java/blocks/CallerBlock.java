/*
   appinventor-java-translation

   Originally authored by Joshua Swank at the University of Alabama
   Work supported in part by NSF award #0702764 and a Google 2011 CS4HS award

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package android.java.blocks;

import android.java.blocks.annotation.BlockAnnotation;
import android.java.code.CodeSegment;
import android.java.code.FunctionCall;
import android.java.code.Value;
import android.java.code.ValueStatement;
import java.util.ArrayList;
import org.w3c.dom.Node;

@BlockAnnotation( genusPattern = "caller.*" )

/**
 *
 * @author Joshua
 */
public class CallerBlock extends Block
{
    public CallerBlock( Node node )
    {
        super( node );
    }

    public CodeSegment generateCode()
    {
        if( getGenus().equals( "caller-command" ))
            return new ValueStatement( createFunction() );
        else
            return createFunction();
    }

    private FunctionCall createFunction()
    {
        ArrayList<Value> params = new ArrayList<Value>();

        for( BlockConnector c : connectors )
            if( !(c instanceof Plug ))
                if( c.hasConnectedBlock())
                    params.add( (Value)(c.getConnectedBlock().generateCode()) );
                else
                    params.add( new Value( "null" ));

        return new FunctionCall( getLabel(), params );
    }
}